package com.maxx.nordvaest.utils

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.AsyncTask
import android.os.Environment
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.FileProvider
import com.maxx.nordvaest.BuildConfig
import com.maxx.nordvaest.R
import com.maxx.nordvaest.utils.Constants.Companion.NotificationChannelId
import okhttp3.ResponseBody
import org.jetbrains.anko.toast
import java.io.*

class DownloadUtils {

    interface ParseCompleteListener {
        fun onComplete(isWrittenToDisk: Boolean)
    }

    companion object {

        var mPdfFileName: String? = null
        var mPdfFile: File? = null

        fun parseDownloadedFile(body: ResponseBody, context: Context?, title: String, listener: ParseCompleteListener) {
            mPdfFileName = ""
            object : AsyncTask<Void, Void, Boolean>() {
                override fun doInBackground(vararg voids: Void): Boolean {
                    return writeResponseBodyToDisk(body, context, title)
                }

                override fun onPostExecute(isWrittenToDisk: Boolean) {
                    listener.onComplete(isWrittenToDisk)
                    if (isWrittenToDisk)
                        createNotification(
                            title = mPdfFileName.toString(),
                            context = context,
                            receiptFile = mPdfFile
                        )
                }
            }.execute()
        }

        private fun writeResponseBodyToDisk(body: ResponseBody, context: Context?, title: String): Boolean {
            try {
                var fileName = title
                var receiptFile = File(getParentFolder(), "$fileName.pdf")
                var i: Int = 1
                if (fileName == "Invoice") {
                    while (receiptFile.exists()) {
                        fileName = title + i
                        receiptFile = File(getParentFolder(), "$fileName.pdf")
                        i++
                    }
                }
                mPdfFile = receiptFile
                mPdfFileName = fileName

                var inputStream: InputStream? = null
                var outputStream: OutputStream? = null

                try {
                    val fileReader = ByteArray(4096)

                    val fileSize = body.contentLength()
                    var fileSizeDownloaded: Long = 0

                    inputStream = body.byteStream()
                    outputStream = FileOutputStream(receiptFile)

                    while (true) {
                        val read = inputStream!!.read(fileReader)

                        if (read == -1) {
                            break
                        }

                        outputStream!!.write(fileReader, 0, read)

                        fileSizeDownloaded += read.toLong()

                        if (BuildConfig.DEBUG)
                            Log.d("TAG", "file download: $fileSizeDownloaded of $fileSize")
                    }

                    outputStream!!.flush()

                    return true
                } catch (e: IOException) {
                    return false
                } finally {
                    if (inputStream != null) {
                        inputStream!!.close()
                    }

                    if (outputStream != null) {
                        outputStream!!.close()
                    }
                }
            } catch (e: IOException) {
                return false
            }

        }

        fun getParentFolder(): File {
            val folder = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path)
            if (!folder?.exists())
                folder.mkdir()
            return folder

        }

        fun writeResponseBodyToDisk1(body: ResponseBody, context: Context?, title: String): Boolean {
            try {
                var fileName = title
                var receiptFile = File(getAlbumStorageDir(context), "$fileName.pdf")
                // receiptFile.createNewFile()
                var i: Int = 1
                if (fileName == "Invoice") {
                    while (receiptFile.exists()) {
                        fileName = title + i
                        receiptFile = File(getAlbumStorageDir(context), "$fileName.pdf")
                        i++
                    }
                }


                var inputStream: InputStream? = null
                var outputStream: OutputStream? = null

                try {
                    val fileReader = ByteArray(4096)

                    val fileSize = body.contentLength()
                    var fileSizeDownloaded: Long = 0

                    inputStream = body.byteStream()
                    outputStream = FileOutputStream(receiptFile)

                    while (true) {
                        val read = inputStream!!.read(fileReader)

                        if (read == -1) {
                            break
                        }

                        outputStream.write(fileReader, 0, read)

                        fileSizeDownloaded += read.toLong()

                        Log.v("TAG", "file download: $fileSizeDownloaded of $fileSize")
                    }

                    outputStream.flush()

                    //createNotification(fileName, context, receiptFile)

                    return true
                } catch (e: IOException) {
                    return false
                } finally {
                    if (inputStream != null) {
                        inputStream.close()
                    }

                    if (outputStream != null) {
                        outputStream.close()
                    }
                }
            } catch (e: IOException) {
                return false
            }

        }


        /**
         * get album storage directory
         */
        fun getAlbumStorageDir(context: Context?): File {
            val file =
                File(Environment.getExternalStorageDirectory(), context?.getString(R.string.image_directory_name))
            if (!file.exists()) {
                file.mkdirs()
            }
            return file
        }

        /**
         * get image title
         *
         */
        fun getImageTitle(title: String): String {
            return String.format("%s_%d.jpg", title, System.currentTimeMillis())
        }

        /**
         * create notification
         */
        private fun createNotification(title: String?, context: Context?, receiptFile: File?) {
            if (title?.isNullOrEmpty() == true || context == null || receiptFile == null) {
                context?.toast("Error while downloading file. Please contact customer support.")
                return
            }
            val intent = Intent(Intent.ACTION_VIEW)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            val result = FileProvider.getUriForFile(
                context,
                context.packageName + ".mmremit.fileprovider",
                receiptFile
            )
            intent.setDataAndType(result, "application/pdf")
            val resultIntent = PendingIntent.getActivity(
                context, 0, intent,
                PendingIntent.FLAG_ONE_SHOT
            )

            val notificationSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val mNotificationBuilder = NotificationCompat.Builder(context, NotificationChannelId)
                .setSmallIcon(R.drawable.ic_file_download_black_24dp)
                .setContentTitle("$title.pdf")
                .setContentText(context.getString(R.string.download_complete))
                .setAutoCancel(true)
                .setChannelId(NotificationChannelId)
                .setSound(notificationSoundURI)
                .setContentIntent(resultIntent)

            val notificationManager = CommonUtils.getNotificationManager(context)

            notificationManager.notify(0, mNotificationBuilder.build())
        }
    }

}
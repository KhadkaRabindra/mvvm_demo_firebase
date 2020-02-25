object Dependencies {

    val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    val benManes = "com.github.ben-manes:gradle-versions-plugin:${Versions.benManes}"
    val spotless = "com.diffplug.spotless:spotless-plugin-gradle:${Versions.spotless}"

    val supportv4 = "androidx.legacy:legacy-support-v4:${Versions.support_version}"
    val appcompat = "androidx.appcompat:appcompat:${Versions.support_version}"
    val material = "com.google.android.material:material:${Versions.support_version}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.support_version}"
    val cardview = "androidx.cardview:cardview:${Versions.support_version}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"

    //rx
    val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"

    //timber
    val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"

    //crashlytics
    val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlyticsVersion}@aar"

    //dagger
    val dagger = "com.google.dagger:dagger-android:${Versions.daggerVersion}"
    val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"
    // if you use the support libraries
    val daggerAnnotation = "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"
    //for annotationProcessor
    val daggerApt = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"

    //net
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
    val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    val rxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    val httpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.httpInterceptor}"

    //picasso
    val picasso = "com.squareup.picasso:picasso:${Versions.picassoVersion}"

    //coroutines
    val kotlinxCorotinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"

    // anko
    val anko = "org.jetbrains.anko:anko:${Versions.anko_version}"
    val ankoCoroutines = "org.jetbrains.anko:anko-coroutines:${Versions.anko_version}"
    val ankoCommons = "org.jetbrains.anko:anko-commons:${Versions.anko_version}"

    //arch-comp Lifecycles, LiveData, and ViewModel
    val lifeCycleExtensions = "android.arch.lifecycle:extensions:${Versions.lifecycle_version}"
    val lifeCycleCommonJava8 = "android.arch.lifecycle:common-java8:${Versions.lifecycle_version}"

    //arch-comp Room
    val roomRuntime = "android.arch.persistence.room:runtime:${Versions.room_version}"
    val roomCompiler = "android.arch.persistence.room:compiler:${Versions.room_version}"
    //Paging
    val pagingRuntime = "android.arch.paging:runtime:${Versions.paging_version}"

    // data binding
    val datanbindingCompiler = "com.android.databinding:compiler:${Versions.databindingCompilerVersion}"

    val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinVersion}"

    //Calligraphy for custom font
    val calligraphy = "uk.co.chrisjenx:calligraphy:${Versions.calligraphyVersion}"

    //Round imageview
    val roundImageView = "com.makeramen:roundedimageview:${Versions.roundImageViewVersion}"

    //Spinner
    val materialSpinner = "com.github.ganfra:material-spinner:${Versions.materialSpinnerVersion}"

    //Form validations
    val databindingValidator = "com.github.Ilhasoft:data-binding-validator:${Versions.databindingValidatorVersion}"

    //Preference room
    val prefRoom = "com.github.skydoves:preferenceroom:${Versions.preferenceRoomVersion}"
    val prefRoomProcessor = "com.github.skydoves:preferenceroom-processor:${Versions.preferenceRoomVersion}"

    //Circle imageview
    val circleImageView = "de.hdodenhof:circleimageview:${Versions.circleImageViewVersion}"

    //multidex
    val multidex = "com.android.support:multidex:${Versions.multidexVersion}"

}
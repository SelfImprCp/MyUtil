# MyUtil
#########################################这是一个软件工具包###################################################
1.用法
 app/build.gradle 中添加

allprojects {
    repositories {

        maven { url "https://jitpack.io" }
    }
}

   compile 'com.github.SelfImprCp:MyUtil:v1.0.4'

2.注意事项：
 因为包中已经添加：  权限
   android:name="android.permission.CAMERA"
 所以使用此包的项目中不用再重复添加


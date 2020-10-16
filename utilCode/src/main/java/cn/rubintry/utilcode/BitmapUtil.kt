package cn.rubintry.utilcode

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import java.io.*

/**
 * @author logcat
 * 图像处理工具类
 */
class BitmapUtil {
    private var size: String? = null

    /**
     * 质量压缩
     *
     * @param bitmap
     * @return
     */
    fun compressQuality(bitmap: Bitmap, quality: Int): Bitmap? {
        var anotherBitmap: Bitmap? = null
        size = bitmap.byteCount.toString() + "byte"
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos)
        val bytes = bos.toByteArray()
        anotherBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        return anotherBitmap
    }

    /**
     * 采样率压缩
     *
     * @param path
     * @return
     */
    fun compressSampling(path: String?): Bitmap {
        val bmp: Bitmap
        val options = BitmapFactory.Options()
        options.inSampleSize = 2
        bmp = BitmapFactory.decodeFile(path, options)
        return bmp
    }

    fun bitmap2File(bitmap: Bitmap? , fileName : String , filePath: String): File {
        return bitmap2File(bitmap!!, 50 , fileName , filePath)!!
    }

    fun bitmap2File(bitmap: Bitmap, quality: Int, fileName: String?, filePath: String?): File? {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos)
        val storageDir = File(filePath)
        var imageFile: File? = null
        if (!storageDir.exists()) {
            storageDir.mkdirs()
        }
        try {
            imageFile = File.createTempFile(fileName, ".png", storageDir)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        try {
            val fos = FileOutputStream(imageFile)
            val inputStream: InputStream = ByteArrayInputStream(baos.toByteArray())
            var x = 0
            val b = ByteArray(1024 * 100)
            while (inputStream.read(b).also { x = it } != -1) {
                fos.write(b, 0, x)
            }
            fos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return imageFile
    }

    /**
     * 读取图片旋转的角度
     * @param path
     * @return
     */
    fun readPictureDegree(path: String?): Int {
        var degree = 0
        try {
            val exifInterface = ExifInterface(path!!)
            val orientation = exifInterface.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> degree = 90
                ExifInterface.ORIENTATION_ROTATE_180 -> degree = 180
                ExifInterface.ORIENTATION_ROTATE_270 -> degree = 270
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return degree
    }

    fun rotate(angle: Int, bitmap: Bitmap): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle.toFloat())
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    fun getRealPhoto(path: String?): Bitmap {
        val degree = readPictureDegree(path)
        val bitmap = BitmapFactory.decodeFile(path)
        return rotate(degree, bitmap)
    }

    fun getRealPhotoFile(imageFile: File, quality: Int): File {
        val bitmap = getRealPhoto(imageFile.absolutePath)
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos)
        try {
            val fos = FileOutputStream(imageFile)
            val `is`: InputStream = ByteArrayInputStream(baos.toByteArray())
            var x = 0
            val b = ByteArray(1024 * 100)
            while (`is`.read(b).also { x = it } != -1) {
                fos.write(b, 0, x)
            }
            fos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return imageFile
    }


    companion object {
        @Volatile
        var instance: BitmapUtil? = null
            get() {
                if (field == null) {
                    synchronized(BitmapUtil::class.java) {
                        if (field == null) {
                            field = BitmapUtil()
                        }
                    }
                }
                return field
            }
            private set
    }
}
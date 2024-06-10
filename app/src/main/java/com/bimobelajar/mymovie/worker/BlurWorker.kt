package com.bimobelajar.mymovie.worker

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File
import java.io.FileOutputStream
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur

class BlurWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        val imagePath = inputData.getString("image_path")
        if (imagePath != null) {
            val bitmap = BitmapFactory.decodeFile(imagePath)
            val blurredBitmap = blurBitmap(bitmap)
            FileOutputStream(File(imagePath)).use { out ->
                blurredBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            }
            return Result.success()
        }
        return Result.failure()
    }

    private fun blurBitmap(bitmap: Bitmap): Bitmap {
        val width = Math.round(bitmap.width * 0.1f)
        val height = Math.round(bitmap.height * 0.1f)
        val inputBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false)
        val outputBitmap = Bitmap.createBitmap(inputBitmap)

        val rs = RenderScript.create(applicationContext)
        val theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        val tmpIn = Allocation.createFromBitmap(rs, inputBitmap)
        val tmpOut = Allocation.createFromBitmap(rs, outputBitmap)
        theIntrinsic.setRadius(25f)
        theIntrinsic.setInput(tmpIn)
        theIntrinsic.forEach(tmpOut)
        tmpOut.copyTo(outputBitmap)

        return Bitmap.createScaledBitmap(outputBitmap, bitmap.width, bitmap.height, false)
    }
}

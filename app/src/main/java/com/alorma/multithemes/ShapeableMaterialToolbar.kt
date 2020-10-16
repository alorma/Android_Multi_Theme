package com.alorma.multithemes
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build.VERSION_CODES
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.RequiresApi
import androidx.annotation.StyleRes
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import com.google.android.material.R
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.MaterialShapeUtils
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.shape.Shapeable

class ShapeableMaterialToolbar @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  @AttrRes defStyleAttr: Int = R.attr.toolbarStyle,
  @StyleRes defStyleRes: Int = R.style.Widget_MaterialComponents_Toolbar,
) : Toolbar(context, attrs, defStyleAttr), Shapeable {

  private var shapeAppearanceModel: ShapeAppearanceModel
  private val materialShapeDrawable: MaterialShapeDrawable

  init {
    shapeAppearanceModel = ShapeAppearanceModel.builder(context, attrs, defStyleAttr, defStyleRes).build()
    materialShapeDrawable = MaterialShapeDrawable(shapeAppearanceModel)

    initBackground(context)
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    MaterialShapeUtils.setParentAbsoluteElevation(this)
  }

  @RequiresApi(VERSION_CODES.LOLLIPOP)
  override fun setElevation(elevation: Float) {
    super.setElevation(elevation)
    MaterialShapeUtils.setElevation(this, elevation)
  }

  private fun initBackground(context: Context) {
    val background = background
    if (background != null && background !is ColorDrawable) {
      return
    }

    val backgroundColor = if (background != null) {
      (background as ColorDrawable).color
    } else {
      Color.TRANSPARENT
    }
    materialShapeDrawable.fillColor = ColorStateList.valueOf(backgroundColor)
    materialShapeDrawable.initializeElevationOverlay(context)
    materialShapeDrawable.elevation = ViewCompat.getElevation(this)
    ViewCompat.setBackground(this, materialShapeDrawable)
  }

  override fun getShapeAppearanceModel(): ShapeAppearanceModel = shapeAppearanceModel

  override fun setShapeAppearanceModel(shapeAppearanceModel: ShapeAppearanceModel) {
    this.shapeAppearanceModel = shapeAppearanceModel
    materialShapeDrawable.shapeAppearanceModel = shapeAppearanceModel
  }
}
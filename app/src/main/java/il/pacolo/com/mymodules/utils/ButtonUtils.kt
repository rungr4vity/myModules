package il.pacolo.com.mymodules.utils

import android.view.View
import android.widget.Button

object ButtonUtils {


    fun onClick(button: Button, onClickListener:(View)-> Unit) {
        button.setOnClickListener(onClickListener)
    }


}
package com.example.myprojectnotesapp.voice.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import com.example.myprojectnotesapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import java.io.File


class BottomSheet: BottomSheetDialogFragment {

    interface OnClickListener {
        fun onCancelClicked()
        fun onOkClicked(filePath: String, filename: String)
    }

    private lateinit var listener: OnClickListener

    private lateinit var filename: String
    private lateinit var dirPath: String

    constructor(dirPath: String, filename : String, listener: OnClickListener){
        this.dirPath = dirPath
        this.filename = filename
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.bottom_sheet, container)
        var editText = view.findViewById<TextInputEditText>(R.id.filenameInput)


        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


        filename = filename.split(".mp3")[0]
        editText.setText(filename)

        showKeyboard(editText)

        view.findViewById<Button>(R.id.okBtn).setOnClickListener {
            // hide keyboard
            hideKeyboard(view)

            val updatedFilename = editText.text.toString()
            if(updatedFilename != filename){
                var newFile = File("$dirPath$updatedFilename.mp3")
                File(dirPath+filename).renameTo(newFile)
            }

            // add entry to db

            // dismiss dialog
            dismiss()

            // fire ok callback
            listener.onOkClicked("$dirPath$updatedFilename.mp3", updatedFilename)
        }

        view.findViewById<Button>(R.id.cancelBtn).setOnClickListener {
            // hide keyboard
            hideKeyboard(view)
            // delete file from storage
            File(dirPath+filename).delete()

            // dismiss dialog
            dismiss()
            // fire cancel callback
            listener.onCancelClicked()
        }

        return view

    }

    private fun showKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }
    }

    private fun hideKeyboard(view: View) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
package com.example.myprojectnotesapp.home.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myprojectnotesapp.MainActivity
import com.example.myprojectnotesapp.R
import com.example.myprojectnotesapp.databinding.ActivityDetailNoteBinding
import com.example.myprojectnotesapp.databinding.BottomsheetNoteBinding
import com.example.myprojectnotesapp.entity.Note
import com.example.myprojectnotesapp.method.DateChange
import com.example.myprojectnotesapp.viewModel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class DetailNoteActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDetailNoteBinding
    val editNoteExtra = "edit_note_extra"
    private lateinit var note: Note
    private lateinit var notesViewModel: NotesViewModel
    private val dateChange = DateChange()
    private lateinit var dialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initViewModel()
        initListener()

    }

    private fun initView() {

        if (intent.getParcelableExtra<Note>(editNoteExtra) != null) {

            note = intent.getParcelableExtra(editNoteExtra)!!
            binding.tvTitle.text = note.title
            binding.tvDate.text = dateChange.changeFormatDate(note.date)
            binding.tvBody.text = note.body

            if (note.updatedDate.isNotEmpty()) {
                if (dateChange.getToday() == note.updatedDate) {
                    binding.tvEdited.text =
                        "Last edit ${dateChange.changeFormatDate(note.updatedTime)}"
                } else {
                    binding.tvEdited.text =
                        "Last edit ${dateChange.changeFormatDate(note.updatedDate)}"
                }
            } else {
                if (dateChange.getToday() == note.date) {
                    binding.tvEdited.text =
                        "Last edit ${dateChange.changeFormatDate(note.time)}"
                } else {
                    binding.tvEdited.text =
                        "Last edit ${dateChange.changeFormatDate(note.date)}"
                }
            }

        }

    }

    private fun initViewModel() {
        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
    }

    private fun initListener() {
        binding.toolbar.nibBack.setOnClickListener(this)
        binding.toolbar.nibEdit.setOnClickListener(this)
        binding.ibMenu.setOnClickListener(this)
    }

    private fun deleteNote(note: Note) {
        notesViewModel.deleteNote(note)
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
        Toast.makeText(this@DetailNoteActivity, "Note removed", Toast.LENGTH_SHORT).show()
    }

    private fun showBottomSheet() {
        val views: View =
            LayoutInflater.from(this).inflate(R.layout.bottomsheet_note, null)

        val bindingBottom = BottomsheetNoteBinding.bind(views)

        dialog = BottomSheetDialog(this)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(views)
        dialog.show()

        bindingBottom.clDelete.setOnClickListener(this)
        bindingBottom.clShare.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.nib_back -> {
                onBackPressed()
            }
            R.id.nib_edit -> {
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra(EditActivity().editNoteExtra, note)
                startActivity(intent)
            }
            R.id.ib_menu -> {
                showBottomSheet()
            }
            R.id.cl_delete -> {
                deleteNote(note)
            }
            R.id.cl_share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "${note.title}\n\n${note.body}")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
    }
}
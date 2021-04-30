package com.example.tictaktoe

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , OnClickListener  {
    var Player = true
    var turn_count=0
    var board_status=Array(3){IntArray(3)}
    lateinit var board:Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board= arrayOf(
            arrayOf(button,button1,button2),
            arrayOf(button3,button4,button5),
            arrayOf(button6,button7,button8)
        )
        for(i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }
        initialiseBoardStatus()
        resetbutton.setOnClickListener{
            turn_count=0
            Player = true
            initialiseBoardStatus()
            textView.text="Player X's Turn "
        }
    }

    private fun initialiseBoardStatus() {
        for(i in 0..2){
            for(j in 0..2){
                board_status[i][j]= -1
                board[i][j].isEnabled = true
                board[i][j].text=""
            }
        }
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when(view.id){
                R.id.button -> {
                    updateValues(row = 0 ,col=0,player = Player )
                }
                R.id.button1 -> {
                    updateValues(row = 0 ,col=1,player = Player )
                }
                R.id.button2 -> {
                    updateValues(row = 0 ,col=2,player = Player )
                }
                R.id.button3 -> {
                    updateValues(row = 1 ,col=0,player = Player )
                }
                R.id.button4 -> {
                    updateValues(row = 1 ,col=1,player = Player )
                }
                R.id.button5 -> {
                    updateValues(row = 1 ,col=2,player = Player )
                }
                R.id.button6 -> {
                    updateValues(row = 2 ,col=0,player = Player )
                }
                R.id.button7 -> {
                    updateValues(row = 2 ,col=1,player = Player )
                }
                R.id.button8 -> {
                    updateValues(row = 2 ,col=2,player = Player )
                }

            }
            turn_count++
            Player=!Player
            if(Player){
                updateDisplay("Player X's Turn")
            }
            else{
                updateDisplay("Player O's Turn")
            }
            if(turn_count==9){
                updateDisplay("Match Drawn")
            }
            checkWinner()

        }
    }

    private fun checkWinner() {
        for (i in 0..2) {
            if (board_status[i][0] == board_status[i][1]&&board_status[i][0] == board_status[i][2]){
                if(board_status[i][0]==1){
                    updateDisplay("Player X Won")
                    break
                }
                else if(board_status[i][0]==0){
                    updateDisplay("Player O Won")
                    break
                }
            }
            if(board_status[0][i] == board_status[1][i] && board_status[0][i] == board_status[2][i]){
                if(board_status[0][i]==1){
                    updateDisplay("Player X Won")
                    break
                }
                else if(board_status[0][i]==0){
                    updateDisplay("Player O Won")
                    break
                }
            }
        }
        if(board_status[0][0] == board_status[1][1] && board_status[0][0] == board_status[2][2]){
            if(board_status[0][0]==1){
                updateDisplay("Player X Won")
            }
            else if(board_status[0][0]==0){
                updateDisplay("Player O Won")
            }
        }
        if(board_status[0][2] == board_status[1][1] && board_status[0][2] == board_status[2][0]){
            if(board_status[0][2]==1){
                updateDisplay("Player X Won")
            }
            else if(board_status[0][2]==0){
                updateDisplay("Player O Won")
            }
        }
    }

    private fun updateDisplay(text: String) {
        textView.text=text
        if(text.contains("Won")){
            disableButton()
        }


    }

    private fun disableButton() {
        for(i in board){
            for(button in i){
                button.isEnabled=false
            }
        }
    }

    private fun updateValues(row: Int, col: Int, player: Boolean) {
        val text=  if(player)"X" else "O"
        val value=  if(player) 1 else 0
            board[row][col].apply{
                isEnabled=false
                setText(text)
            }
        board_status[row][col]=value

    }
}
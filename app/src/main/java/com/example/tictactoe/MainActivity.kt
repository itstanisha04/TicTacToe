package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {



    var Player = true
    var count_turn = 0
    lateinit var displayTv1:TextView
    lateinit var board: Array<Array<Button>>
    var boardstatus = Array(3){IntArray(3)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val displayTv = findViewById<TextView>(R.id.displayTv)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val resetBtn = findViewById<Button>(R.id.resetBtn)

        displayTv1 = displayTv
        board = arrayOf(
                    arrayOf(button1, button2, button3),
                    arrayOf(button4, button5, button6),
                    arrayOf(button7, button8, button9)
                 )


        initialize()
        for(i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }
        resetBtn.setOnClickListener{
            Player = true
            displayTv.text = "PLAYER X TURN"
            count_turn = 0
            initialize()
        }
    }


    private fun initialize() {
        for(i in 0..2){
            for(j in 0..2){
                boardstatus[i][j]= -1
                board[i][j].isEnabled = true
                board[i][j].text=""
            }
        }
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.button1->{
                    update(row = 0, col = 0, player = Player)
            }
            R.id.button2->{
                    update(row = 0, col = 1, player = Player)
            }
            R.id.button3->{
                update(row = 0, col = 2, player = Player)

            }
            R.id.button4->{
                update(row = 1, col = 0, player = Player)

            }
            R.id.button5->{
                update(row = 1, col = 1, player = Player)

            }
            R.id.button6->{
                update(row = 1, col = 2, player = Player)

            }
            R.id.button7->{
                update(row = 2, col = 0, player = Player)

            }R.id.button8-> {
            update(row = 2, col = 1, player = Player)

            }
            R.id.button9-> {
                update(row = 2, col = 2, player = Player)

            }
        }
        Player = !Player
        count_turn++
        if(Player) {
            displayTv1.text = "PLAYER X TURN"
        } else{
            displayTv1.text = "PLAYER O TURN"
        }

        if(count_turn == 9){
            displayTv1.text = "GAME DRAW"
        }
        checkWinner()
        if(displayTv1.text.contains("WINS")){
            disableButtons()
        }
    }

    private fun disableButtons() {
        for(i in 0..2){
            for(j in 0..2){
                board[i][j].apply{
                    isEnabled = false
                }
            }
        }
    }

    private fun checkWinner() {
        for(i in 0..2){
            if(boardstatus[i][0] == boardstatus[i][1] && boardstatus[i][0] == boardstatus[i][2]){
                if(boardstatus[i][0] == 1){
                    displayTv1.text = "PLAYER X WINS"
                    break
                } else if(boardstatus[i][0] == 0){
                    displayTv1.text = "PLAYER O WINS"
                    break
                }
            }
        }
        for(i in 0..2){
            if(boardstatus[0][i] == boardstatus[1][i] && boardstatus[0][i] == boardstatus[2][i]){
                if(boardstatus[0][i] == 1){
                    displayTv1.text = "PLAYER X WINS"
                    break
                } else if(boardstatus[0][i] == 0){
                    displayTv1.text = "PLAYER O WINS"
                    break
                }
            }
        }
        if(boardstatus[0][0]==boardstatus[1][1] && boardstatus[1][1]==boardstatus[2][2]){
            if(boardstatus[0][0] == 1){
                displayTv1.text = "PLAYER X WINS"
            } else if(boardstatus[0][0] == 0){
                displayTv1.text = "PLAYER O WINS"
            }
        }
        else if(boardstatus[0][2]==boardstatus[1][1] && boardstatus[1][1]==boardstatus[2][0]){
            if(boardstatus[0][2] == 1){
                displayTv1.text = "PLAYER X WINS"
            } else if(boardstatus[0][2] == 0){
                displayTv1.text = "PLAYER O WINS"
            }
        }
    }


    private fun update(row: Int, col: Int, player: Boolean) {
        val text = if(player) "X" else "O"
        val value = if(player) 1 else 0
        board[row][col].apply {
            setText(text)
            isEnabled = false
        }
        boardstatus[row][col] = value
    }
}
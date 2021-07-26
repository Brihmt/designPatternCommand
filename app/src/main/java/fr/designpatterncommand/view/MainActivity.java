package fr.designpatterncommand.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Arrays;

import fr.designpatterncommand.R;
import fr.designpatterncommand.model.ManagerAction;
import fr.designpatterncommand.model.Movement;
import fr.designpatterncommand.model.command.ActionBottom;
import fr.designpatterncommand.model.command.ActionLeft;
import fr.designpatterncommand.model.command.ActionRight;
import fr.designpatterncommand.model.command.ActionTop;
import fr.designpatterncommand.model.command.Command;
import fr.designpatterncommand.model.dragShadowBuilder.MyDragShadowBuilder;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewTop, imageViewBottom, imageViewLeft, imageViewRight;
    private LinearLayout ll2,ll3;
    private Button btnStart, btnRestart;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private int img[] = {};

    private ManagerAction manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager=new ManagerAction();

        imageViewTop=(ImageView)findViewById(R.id.imgViewTop);
        imageViewBottom=(ImageView)findViewById(R.id.imgViewBottom);
        imageViewLeft=(ImageView)findViewById(R.id.imgViewLeft);
        imageViewRight=(ImageView)findViewById(R.id.imgViewRight);


        ll2=(LinearLayout)findViewById(R.id.ll2);
        ll3=(LinearLayout)findViewById(R.id.ll3);
        btnStart=(Button)findViewById(R.id.btnStart);
        btnRestart=(Button)findViewById(R.id.btnRestart);
        recyclerView=(RecyclerView)findViewById(R.id.rclView);
        
        MyDragEventListener myDragEventListener = new MyDragEventListener();
        imageViewTop.setOnDragListener(myDragEventListener);
        imageViewBottom.setOnDragListener(myDragEventListener);
        imageViewLeft.setOnDragListener(myDragEventListener);
        imageViewRight.setOnDragListener(myDragEventListener);
        ll2.setOnDragListener(myDragEventListener);
        ll3.setOnDragListener(myDragEventListener);
        adapter = new MyAdapter(this, img);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));


        imageViewTop.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item("Top");

                ClipData dragData = new ClipData(
                        (CharSequence) v.getTag(),
                        new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN } ,
                        item);
                View.DragShadowBuilder myShadow = new MyDragShadowBuilder(imageViewTop);
                v.startDrag(dragData,myShadow,null,0);
                return true;

            }

        }
        );

        imageViewBottom.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item("Bottom");

                ClipData dragData = new ClipData(
                        (CharSequence) v.getTag(),
                        new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN } ,
                        item);
                View.DragShadowBuilder myShadow = new MyDragShadowBuilder(imageViewBottom);
                v.startDrag(dragData,myShadow,null,0);
                return true;
            }
        }
        );

        imageViewLeft.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item("Left");

                ClipData dragData = new ClipData(
                        (CharSequence) v.getTag(),
                        new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN } ,
                        item);
                View.DragShadowBuilder myShadow = new MyDragShadowBuilder(imageViewLeft);
                v.startDrag(dragData,myShadow,null,0);
                return true;
            }
        }
        );

        imageViewRight.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item("Right");

                ClipData dragData = new ClipData(
                        (CharSequence) v.getTag(),
                        new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN } ,
                        item);
                View.DragShadowBuilder myShadow = new MyDragShadowBuilder(imageViewRight);
                v.startDrag(dragData,myShadow,null,0);
                return true;
            }
        }
        );
    }

    public void addImg(int img){
        this.img = Arrays.copyOf(this.img, this.img.length+1);
        this.img[this.img.length-1] = img;
    }

    protected class MyDragEventListener implements View.OnDragListener {

        public boolean onDrag(View v, DragEvent event) {

            final int action = event.getAction();

            switch(action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {

                        v.setBackgroundColor(Color.BLUE);
                        v.invalidate();

                        return true;

                    }
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:

                    v.setBackgroundColor(Color.GREEN);
                    v.invalidate();
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:

                    v.setBackgroundColor(Color.BLUE);
                    v.invalidate();

                    return true;

                case DragEvent.ACTION_DROP:
                    ClipData clip=event.getClipData();
                    Log.i("testui",(String)clip.getItemAt(0).getText());
                    Log.i("testui","id: " +v.getId());
                    switch((String)clip.getItemAt(0).getText()){
                        case "Top":
                            manager.addAction(new ActionTop());
                            addImg(R.drawable.arrowtop);
                            break;
                        case "Bottom":
                            manager.addAction(new ActionBottom());
                            addImg(R.drawable.arrowbottom);
                            break;
                        case "Left":
                            manager.addAction(new ActionLeft());
                            addImg(R.drawable.arrowleft);
                            break;
                        case "Right":
                            manager.addAction(new ActionRight());
                            addImg(R.drawable.arrowright);
                            break;
                    }
                    adapter.setImg(img);
                    adapter.notifyDataSetChanged();
                    v.invalidate();
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundColor(Color.WHITE);
                    v.invalidate();

/*                    if (event.getResult()) {
                        //Toast.makeText(this, "The drop was handled.", Toast.LENGTH_LONG).show();

                    } else {
                        //Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_LONG).show();
                    }
*/
                    return true;
                default:
                    Log.e("DragDrop Example","Unknown action type received by OnDragListener.");
                    break;
            }

            return false;
        }
    };

    public void clicButtonStart(View sender){
        FieldView fieldView = (FieldView) findViewById(R.id.fieldView);
        manager.execCommandList(fieldView);
        if(fieldView.isExited()){
            newGame(fieldView);
            fieldView.setExited(false);
        }
    }

    public void clicButtonRestart(View sender){
        manager.delAllAction();
        img = new int[]{};
        adapter.setImg(img);
        adapter.notifyDataSetChanged();
    }

    public void newGame(FieldView sender){
        AlertDialog newGame = new AlertDialog.Builder(this).create();
        newGame.setTitle( "End Game !");
        newGame.setMessage( "You reached the exit! Do you want to play an other game?" );
        newGame.setButton( AlertDialog.BUTTON_POSITIVE, "Yes", new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int which){
                manager.restartGame(sender);
            }
        });
        newGame.setButton( AlertDialog.BUTTON_NEGATIVE, "No", new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int which){
                finish();
            }
        });
        newGame.show();
    }
}

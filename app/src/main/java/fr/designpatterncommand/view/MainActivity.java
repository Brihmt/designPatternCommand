package fr.designpatterncommand.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import fr.designpatterncommand.R;
import fr.designpatterncommand.model.ManagerAction;
import fr.designpatterncommand.model.Movement;
import fr.designpatterncommand.model.command.Command;
import fr.designpatterncommand.model.dragShadowBuilder.MyDragShadowBuilder;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewTop, imageViewBottom, imageViewLeft, imageViewRight;
    private LinearLayout ll2,ll3;
    private Button btnStart;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    //TODO: Ajouter et supprimer les éléments de la liste d'image lors du drag and drop
    private int img[] = {R.drawable.arrowtop};

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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        imageViewTop.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item("0");

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
                    //manager.addAction(Movement.values()[(int)clip.getItemAt(0).getText()]);
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
        //manager.execCommandList();
    }
}

package fr.designpatterncommand.view;

import androidx.appcompat.app.AppCompatActivity;

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
import fr.designpatterncommand.model.dragShadowBuilder.MyDragShadowBuilder;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private LinearLayout ll2,ll3;
    private Button btnStart;

    private ManagerAction manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager=new ManagerAction();

        imageView=(ImageView)findViewById(R.id.imageViewMainWindows);

        ll2=(LinearLayout)findViewById(R.id.ll2);
        ll3=(LinearLayout)findViewById(R.id.ll3);
        btnStart=(Button)findViewById(R.id.btnStart);
        
        MyDragEventListener myDragEventListener = new MyDragEventListener();
        imageView.setOnDragListener(myDragEventListener);
        ll2.setOnDragListener(myDragEventListener);
        ll3.setOnDragListener(myDragEventListener);

        imageView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item("Top");

                ClipData dragData = new ClipData(
                        (CharSequence) v.getTag(),
                        new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN } ,
                        item);
                View.DragShadowBuilder myShadow = new MyDragShadowBuilder(imageView);
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
        //managerAction.execCommandList();
    }
}

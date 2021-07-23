package fr.designpatterncommand.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Range;
import android.view.View;

import java.util.Random;

import fr.designpatterncommand.model.Character;
import fr.designpatterncommand.R;

public class FieldView extends View {
    private Bitmap imgField;
    private Bitmap imgCharacter;
    private int widthZone;

    int x,y;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Character character=new Character();

    public FieldView(Context context) {
        super(context);
        widthZone=1;
        Random a=new Random();
        x= a.nextInt(7)+2;
        y= a.nextInt(4)+2;
    }

    public FieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        widthZone=1;
        Random a=new Random();
        x= a.nextInt(8);
        y= a.nextInt(5);
    }

    @Override
    protected void onSizeChanged( int width, int height, int oldw, int oldh){
        super.onSizeChanged( width, height, oldw, oldh );
        this.widthZone= width/8;
        this.setMinimumHeight((int)widthZone*5);
        imgField = BitmapFactory.decodeResource(getResources(), R.drawable.field);
        imgField = Bitmap.createScaledBitmap(imgField,width,width,true);
    }

    public void drawCharacter(Canvas canvas, Character chara){
        Log.i("test", "J'affiche le personnage là");
        canvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), chara.getImg()),(int)widthZone,(int)widthZone,true),(int)widthZone*chara.getPosX(),(int)widthZone*chara.getPosY(),paint);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawBitmap(Bitmap.createScaledBitmap(imgField,(int)(widthZone)*8,(int)(widthZone)*5,true), 0, 0, paint);
        canvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.door),widthZone,widthZone,true), widthZone*x,widthZone*y,paint);
        drawCharacter(canvas,character);
    }

    public void setCharacter(Character charac){
        character=charac;
    }

    public void setFinish(int x, int y)
    {
        this.x=x;
        this.y=y;
    }
}

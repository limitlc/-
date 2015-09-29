package com.paxw.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.paxw.blue.R;








public class Rado extends RelativeLayout{
	private Context context;
    public Rado(Context context) {
        super(context);
        init(context);
    }

    public Rado(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Rado(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
    	this.context = context;
    	LayoutInflater.from(context).inflate(R.layout.rado_layout, this, true);
    	imageRado = (ImageView) findViewById(R.id.rado_rotate);
    	setAnimation();
    }
    private RotateAnimation rotate;
    private void setAnimation() {
		if (null==rotate) {
			rotate = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		}
		rotate.setDuration(1000);
		rotate.setRepeatCount(-1);
		rotate.setInterpolator(new LinearInterpolator());
		rotate.setRepeatMode(Animation.RESTART);
	}
    private boolean isRunningAnim;
	private ImageView imageRado;
    public void runAnim(){
    	isRunningAnim=true;
    	imageRado.startAnimation(rotate);
    }
    public void stopAnim(){
    	isRunningAnim=false;
    	imageRado.clearAnimation();
    }
    public boolean isRunningAnim(){
    	return isRunningAnim;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    	super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    	int height = this.getMeasuredHeight();
    	if (height==148) return;
    	float ratio = height/(148f);
    	int imageheight = (int) (67*ratio);
    	int imagewidth =(int) (71*ratio);
    	LayoutParams layoutParams = (LayoutParams) imageRado.getLayoutParams();
    	layoutParams.height=imageheight;
    	layoutParams.width = imagewidth;
    	layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
    	imageRado.setLayoutParams(layoutParams);
    	runAnim();
    	
    	
    }
 

}

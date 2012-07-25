package ru.develweb.redbookexamples;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.Toast;

public class OpenGLContext extends Activity {
	private GlRenderer renderer;
	private GLSurfaceView surface;
	public static final String EXAMPLE_NAME = "ExampleName";
	private GestureDetector gestureDetector;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {    	    	
        super.onCreate(savedInstanceState);
        
        gestureDetector = new GestureDetector(this, new GlAppGestureListener(this));
        
        surface = new GLSurfaceView(this);
        renderer = new GlRenderer(this);
        
        Bundle extras = getIntent().getExtras();
                     
        renderer.SetExampleNum(extras.getInt(EXAMPLE_NAME));
        
        if (renderer.GetExampleNum() == 5) {
        	Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, "����������� ������� ����", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, 0, 50);
            toast.show();
        }
        
        surface.setRenderer(renderer);
        setContentView(surface);              
    }
    
    @Override
	public boolean onTouchEvent(MotionEvent event) {
    	if (gestureDetector.onTouchEvent(event)) {			
			return true;
		}
    	
    	return super.onTouchEvent(event);
    }
    
    private class GlAppGestureListener extends GestureDetector.SimpleOnGestureListener {
    	private OpenGLContext glApp;
    	
    	public GlAppGestureListener(OpenGLContext glApp) {
    		this.glApp = glApp;
    	}

		@Override
		public boolean onDoubleTap(MotionEvent e) {		
			
			if (glApp.renderer.GetExampleNum() == 5) {
				glApp.renderer.SwitchAlpha();
			}
			
			return true;
		}
    }
}
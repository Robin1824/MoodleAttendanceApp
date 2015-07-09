package com.example.moodleattendanceapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CourseListAdapter extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	   private Activity activity;
	   private ArrayList data;
	   private static LayoutInflater inflater=null;
	   public Resources res;
	   Course tempValues=null;
	   int i=0;
	    
	   public CourseListAdapter(Activity a, ArrayList d,Resources resLocal) {
	        
	           activity = a;
	           data=d;
	           res = resLocal;
	        
	           /***********  Layout inflator to call external xml layout () ***********/
	            inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        
	   }

	   /******** What is the size of Passed Arraylist Size ************/
	   public int getCount() {
	        
	       if(data.size()<=0)
	           return 1;
	       return data.size();
	   }

	   public Object getItem(int position) {
	       return position;
	   }

	   public long getItemId(int position) {
	       return position;
	   }
	    
	   /********* Create a holder Class to contain inflated xml file elements *********/
	   public static class ViewHolder{
	        
	       public TextView tvCourseName;
	       /*public TextView Workreq;
	       public TextView Sdate;
	       public TextView Edate;*/

	   }

	   /****** Depends upon data size called for each row , Create each ListView row *****/
	   public View getView(int position, View convertView, ViewGroup parent) {
	        
	       View vi = convertView;
	       ViewHolder holder;
	        
	       if(convertView==null){ 
	            
	           /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
	           vi = inflater.inflate(R.layout.course_list_cell_design, null);
	            
	           /****** View Holder Object to contain tabitem.xml file elements ******/

	           holder = new ViewHolder();
	           holder.tvCourseName = (TextView) vi.findViewById(R.id.tvCourseFullName);
	/*           holder.Workreq=(TextView)vi.findViewById(R.id.txtWorkRequest);
	           holder.Sdate=(TextView)vi.findViewById(R.id.txtStartDate);
	           holder.Edate=(TextView)vi.findViewById(R.id.txtEndDate);*/
	          /************  Set holder with LayoutInflater ************/
	           vi.setTag( holder );
	       }
	       else 
	           holder=(ViewHolder)vi.getTag();
	        
	       if(data.size()<=0)
	       {
	           Log.i("if Data size check","ok");
	           holder.tvCourseName.setText("No Data");
	          /* holder.Workreq.setText("");
	           holder.Sdate.setText("");
	           holder.Edate.setText("");
*/	       }
	       else
	       {
	    	   Log.i("else Data size check","ok");
	           /***** Get each Model object from Arraylist ********/
	           tempValues=null;
	           tempValues = ( Course ) data.get( position );
	            
	           /************  Set Model values in Holder elements ***********/

	            holder.tvCourseName.setText(tempValues.getFull_name());
	           /* holder.Workreq.setText(tempValues.getWorkRequest());
	            holder.Sdate.setText(tempValues.getStartDate());
	            holder.Edate.setText(tempValues.getEndDate());*/
	              
	            /******** Set Item Click Listner for LayoutInflater for each row *******/

	            vi.setOnClickListener(new OnItemClickListener( position ));
	       }
	       return vi;
	   }
	    
	   @Override
	   public void onClick(View v) {
	           Log.v("CustomAdapter", "=====Row button clicked=====");
	   }
	    
	   /********* Called when Item click in ListView ************/
	   private class OnItemClickListener  implements OnClickListener{           
	       private int mPosition;
	        
	       OnItemClickListener(int position){
	            mPosition = position;
	       }
	        
	       @Override
	       public void onClick(View arg0) {

	  
	         UserCourseActivity sct = (UserCourseActivity)activity;

	        /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/

	           sct.onItemClick(mPosition);
	       }               
	   }
	
}

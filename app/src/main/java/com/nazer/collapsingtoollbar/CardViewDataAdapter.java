package com.nazer.collapsingtoollbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardViewDataAdapter extends
		RecyclerView.Adapter<CardViewDataAdapter.ViewHolder> {

	private List<Student> stList;

	public CardViewDataAdapter(List<Student> students) {
		this.stList = students;

	}

	// Create new views
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent,
			int viewType) {
		// create a new view
		View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.cardview_row, null);

		// create ViewHolder

		ViewHolder viewHolder = new ViewHolder(itemLayoutView);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {

		viewHolder.tvName.setText(stList.get(position).getName());

		viewHolder.tvEmailId.setText(stList.get(position).getEmailId());
		
		viewHolder.singlestudent=stList.get(position);
		viewHolder.play.setId(position);
		Log.e("play position",""+viewHolder.play.getId());
		viewHolder.play.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				view.getId();
				ImageView imageView = (ImageView) view;
				imageView.setBackgroundResource(android.R.drawable.ic_media_pause);
			}
		});

	}

	// Return the size arraylist
	@Override
	public int getItemCount() {
		return stList.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public TextView tvName;
		public TextView tvEmailId;

		public Student singlestudent;
		public ImageView play;

		public ViewHolder(View itemLayoutView) {
			super(itemLayoutView);

			tvName = (TextView) itemLayoutView.findViewById(R.id.tvName);

			tvEmailId = (TextView) itemLayoutView.findViewById(R.id.tvEmailId);
			play=(ImageView)itemLayoutView.findViewById(R.id.img_play);

			itemLayoutView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					Toast.makeText(	v.getContext(),	"Data : \n" + singlestudent.getName() + " \n"+ singlestudent.getEmailId(),Toast.LENGTH_SHORT).show();

				}
			});

		}

	}

}

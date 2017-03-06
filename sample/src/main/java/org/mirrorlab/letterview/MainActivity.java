package org.mirrorlab.letterview;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.nghiatrx.libs.letterview.SquareLetterView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private String [] mDataset = {"Microsoft", "Apple", "Google", "Amazon", "Samsung", "Facebook", "Yahoo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MyAdapter(mDataset);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private String[] mData;

        public MyAdapter(String[] data) {
            mData = data;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_recycler, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bindData(mData[position]);
        }

        @Override
        public int getItemCount() {
            return mData.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView textView;
            private SquareLetterView squareLetterView;

            public ViewHolder(View view) {
                super(view);
                textView = (TextView) view.findViewById(R.id.text);
//                circleLetterView = (CircleLetterView) view.findViewById(R.id.circleLetterView);
//                circleLetterView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        circleLetterView.runSelectedAnimation();
//                    }
//                });
//
//                view.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        circleLetterView.runSelectedAnimation();
//                    }
//                });

                squareLetterView = (SquareLetterView) view.findViewById(R.id.circleLetterView);

                squareLetterView.setAnimationType(SquareLetterView.ROTATE_ANIMATION);

//                squareLetterView.setSelectedIndicatorBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_call_black_24dp));

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        squareLetterView.runSelectedAnimation();
                    }
                });

            }

            public void bindData(String s) {
                textView.setText(s);
                squareLetterView.setLetter(s.charAt(0));
            }
        }

    }


}

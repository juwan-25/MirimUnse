package kr.hs.s2104.mirimunse;

import android.content.Context;
import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLSessionBindingEvent;

import kr.hs.s2104.mirimunse.FriendItem;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private ArrayList<FriendItem> mFriendList;
    private Context mContext;

    public interface RecyclerViewClickListener {
        public void recyclerViewListClicked(View v, int position);
    }

    public interface OnItemCliskListener {
        void onItemClick(View v, int pos);
    }
    private OnItemCliskListener mListener = null;
    public void setOnItemClickListener(OnItemCliskListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        //return new ViewHolder(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // https://fjdkslvn.tistory.com/11
    @Override
    public void onBindViewHolder(@NonNull MyRecyclerAdapter.ViewHolder holder, int position) {
        //holder.onBind(mFriendList.get(position));

        final FriendItem item = mFriendList.get(position);
        holder.itemView.setTag(position);
        holder.name.setText(mFriendList.get(position).getName());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Context context = v.getContext();
//                Intent intent = new Intent(v.getContext(), LuckRecordDetailActivity.class);
//                v.getContext().startActivity(intent);
//
//            }
//        });
    }

    public void setFriendList(ArrayList<FriendItem> list){
        this.mFriendList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mFriendList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile;
        TextView name;
        ImageView message;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profile = (ImageView) itemView.findViewById(R.id.profile);
            name = (TextView) itemView.findViewById(R.id.name);
            message = (ImageView) itemView.findViewById(R.id.message);

            // 아이템 클릭 이벤트 처리
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition(); // 위치 가져오기
                    if(pos != RecyclerView.NO_POSITION) {   // 아이템이 존재할 때
                       // 리스너 객체의 메서드 호출
                        if (mListener != null) {
                            mListener.onItemClick(v, pos);
                            Context context = v.getContext();
                            Intent intent = new Intent(v.getContext(), LuckRecordDetailActivity.class);
                            v.getContext().startActivity(intent);
                        }
                    }
                }
            });

        }

        void onBind(FriendItem item){
            profile.setImageResource(item.getResourceId());
            name.setText(item.getName());
            message.setImageResource(item.getResourceId());
        }
    }
}


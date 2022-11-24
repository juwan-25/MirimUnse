package kr.hs.s2104.mirimunse.recyclerviewarcodian;

import android.animation.ValueAnimator;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kr.hs.s2104.mirimunse.R;

public class ViewHolderInfo extends  RecyclerView.ViewHolder {

    TextView textTitle, textContent;
    LinearLayout linearlayout;

    OnViewHolderItemClickListener onViewHolderItemClickListener;


    //TODO: 미림운세에 맞춰서 수정하기
    public ViewHolderInfo(@NonNull View itemView) {
        super(itemView);

        textTitle = itemView.findViewById(R.id.text_title);
        textContent = itemView.findViewById(R.id.text_content);
        linearlayout = itemView.findViewById(R.id.linearlayout);

        linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewHolderItemClickListener.onViewHolderItemClick();
            }
        });
    }

    public void onBind(DataInfor data, int position, SparseBooleanArray selectedItems){
        textTitle.setText(data.getTitle());
        textContent.setText(data.getcontext());
        changeVisibility(selectedItems.get(position));
    }

    private void changeVisibility(final boolean isExpanded) {
        // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
        ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, 120) : ValueAnimator.ofInt(120, 0);
        // Animation이 실행되는 시간, n/1000초
        va.setDuration(500);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // imageView의 높이 변경
                textContent.getLayoutParams().height = (int) animation.getAnimatedValue();
                textContent.requestLayout();
                // imageView가 실제로 사라지게하는 부분
                textContent.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
//                // imageView의 높이 변경
//                iv_movie2.getLayoutParams().height = (int) animation.getAnimatedValue();
//                iv_movie2.requestLayout();
//                // imageView가 실제로 사라지게하는 부분
//                iv_movie2.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
            }
        });
        // Animation start
        va.start();
    }

    public void setOnViewHolderItemClickListener(OnViewHolderItemClickListener onViewHolderItemClickListener) {
        this.onViewHolderItemClickListener = onViewHolderItemClickListener;
    }
}
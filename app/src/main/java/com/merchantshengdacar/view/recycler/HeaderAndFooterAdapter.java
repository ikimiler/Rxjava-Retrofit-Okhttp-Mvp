package com.merchantshengdacar.view.recycler;

import android.content.Context;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static com.merchantshengdacar.view.recycler.LoadMoreStatus.LOAD_RUNNING;

/**
 * Created by kimi on 2017/3/8 0008.
 * Email: 24750@163.com
 */

public abstract class HeaderAndFooterAdapter<VH, T> extends RecyclerView.Adapter {

    private List<View> headers = new ArrayList<>();
    private LinkedList<View> footers = new LinkedList<>();

    protected static final int HEADER_TYPE = 1;
    protected static final int FOOTER_TYPE = 2;

    private int headerPosition, footerPosition;

    private OnHeaderAndFooterClickListener onItemClickListener;
    private LoadMoreView loadMore;

    protected Context mContext;
    public T mDatas;

    public HeaderAndFooterAdapter(Context cxt, T data) {
        mContext = cxt;
        this.mDatas = data;
        initFooter();
    }

    private void initFooter() {
        loadMore = new LoadMoreView(mContext);
        loadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (loadMore.currentStatus) {
                    case LOAD_FAILD:
                        if (onItemClickListener != null) {
                            setLoadMoreStatus(LOAD_RUNNING);
                            onItemClickListener.onLoadMore();
                        }
                        break;

                }
            }
        });
        addFooterView(loadMore);
    }


    public void setLoadMoreStatus(LoadMoreStatus status) {
        loadMore.setLoadMoreStatus(status);
    }


    public void setOnItemClickListener(OnHeaderAndFooterClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE) {
            return new HeaderAndFooterViewHolder(headers.get(headerPosition++));
        } else if (viewType == FOOTER_TYPE) {
            return new HeaderAndFooterViewHolder(footers.get(footerPosition++));
        }
        return onCreateViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == HEADER_TYPE) {
            HeaderAndFooterViewHolder headerViewHodler = (HeaderAndFooterViewHolder) holder;
            headerViewHodler.bindView(true, position);
        } else if (itemViewType == FOOTER_TYPE) {
            HeaderAndFooterViewHolder headerViewHodler = (HeaderAndFooterViewHolder) holder;
            headerViewHodler.bindView(false, position);
        } else {
            Message msg = Message.obtain();
            msg.arg1 = position;
            holder.itemView.setTag(msg);
            holder.itemView.setOnClickListener(click);
            setViewHolder(holder, position - headers.size());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position < headers.size()) {
            return HEADER_TYPE;
        } else if (position >= getCount() + headers.size()) {
            return FOOTER_TYPE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return getCount() + headers.size() + footers.size();
    }

    protected abstract int getCount();

    protected abstract void setViewHolder(RecyclerView.ViewHolder holder, int position);

    protected abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);


    public void addHeaderView(@NonNull View v) {
        headers.add(v);
    }

    public void addFooterView(@NonNull View v) {
        footers.addFirst(v);
    }


    public class HeaderAndFooterViewHolder extends RecyclerView.ViewHolder {

        public HeaderAndFooterViewHolder(View itemView) {
            super(itemView);
        }

        public void bindView(boolean isHeader, int position) {
            if (onItemClickListener == null) return;
            Message msg = Message.obtain();
            if (isHeader) {
                msg.arg1 = position;
                msg.obj = true;
                itemView.setTag(msg);
                itemView.setOnClickListener(click);
            } else {
                msg.arg1 = position;
                msg.obj = false;
                itemView.setTag(msg);
            }
        }
    }

    public View.OnClickListener click = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Message msg = (Message) v.getTag();
            if (msg.arg1 >= headers.size() && msg.arg1 < headers.size() + getCount()) {
                onItemClickListener.onItemViewClick(v, msg.arg1 - headers.size());
                return;
            }

            boolean isHader = (boolean) msg.obj;
            if (isHader) {
                onItemClickListener.onHeaderClick(v, msg.arg1);
            } else {
                // onItemClickListener.onFooterClick(v, msg.arg1 - getCount() - headers.size());
            }
        }
    };

}

package com.example.instabookr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//import com.github.johnkil.print.PrintView;
import com.unnamed.b.atv.model.TreeNode;


public class ProfileHolder extends TreeNode.BaseNodeViewHolder<IconTreeItemHolder.IconTreeItem> {

    ImageView iconView;
    public ProfileHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(TreeNode node, IconTreeItemHolder.IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_profile_node, null, false);
        TextView tvValue = (TextView) view.findViewById(R.id.node_value);
        tvValue.setText(value.text);

//        final PrintView iconView = (PrintView) view.findViewById(R.id.icon);
//        iconView.setIconText(context.getResources().getString(value.icon));
        iconView = (ImageView) view.findViewById(R.id.icon);
        iconView.setImageResource(value.icon);

        return view;
    }

    @Override
    public void toggle(boolean active) {
//        iconView.setIconText(context.getResources().getString(active ? R.string.ic_keyboard_arrow_down : R.string.ic_keyboard_arrow_right));
        iconView.setImageResource(active ? R.drawable.arrow_up : R.drawable.arrow_down);
    }

    @Override
    public int getContainerStyle() {
        return R.style.TreeNodeStyleCustom;
    }
}

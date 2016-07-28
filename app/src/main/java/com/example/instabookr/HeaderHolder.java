package com.example.instabookr;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.github.johnkil.print.PrintView;
import com.unnamed.b.atv.model.TreeNode;

public class HeaderHolder extends TreeNode.BaseNodeViewHolder<IconTreeItemHolder.IconTreeItem> {

//    private PrintView arrowView;
    Context context;
    ImageView iconView;

    public HeaderHolder(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View createNodeView(TreeNode node, final IconTreeItemHolder.IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_header_node, null, false);
        TextView tvValue = (TextView) view.findViewById(R.id.node_value);
        tvValue.setText(value.text);


        iconView = (ImageView) view.findViewById(R.id.icon);
        iconView.setImageResource(value.icon);

        if (node.isLeaf()) {
//            arrowView.setVisibility(View.INVISIBLE);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Clicked on value "+value.text,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,ServiceListActivity.class);
                intent.putExtra("service_name",value.text);
                intent.putExtra("service_uuid","1231231231qwertyuiop");
                context.startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void toggle(boolean active) {
//        arrowView.setIconText(context.getResources().getString(active ? R.string.ic_keyboard_arrow_down : R.string.ic_keyboard_arrow_right));
    }


}

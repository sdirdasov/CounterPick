package com.example.counterpick.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.counterpick.R
import com.example.domain.models.Hero
import java.util.*

class HeroAdapter: RecyclerView.Adapter<HeroAdapter.ViewHolder>() {

    private val mHeroList: MutableList<Hero> = LinkedList()

    fun setData(newHeroes: List<Hero>) {
        mHeroList.clear()
        mHeroList.addAll(newHeroes)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_hero, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return mHeroList.count()
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(model = mHeroList[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val txtTitle: TextView = itemView.findViewById(R.id.txtHeroTitle)
        private val txtAtackType: TextView = itemView.findViewById(R.id.txtHeroAttackType)
        private val imgAvatar: ImageView = itemView.findViewById(R.id.imgAvatar)


        fun bind(model: Hero) {
            txtTitle.text = model.title

            if (model.attackType == 0) {
                txtAtackType.text = itemView.context.getString(R.string.attack_type_melee)
            } else {
                txtAtackType.text = itemView.context.getString(R.string.attack_type_ranged)
            }
        }
    }
}

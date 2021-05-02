package com.falco.sbhw_16_8

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ElectronicsAdapter(
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var electronics: List<Electronics> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_SP -> SmartphoneHolder(parent.inflate(R.layout.item_smartphone), onItemClick)
            TYPE_TV -> TelevisionHolder(parent.inflate(R.layout.item_tv), onItemClick)
            TYPE_GC -> GameConsoleHolder(parent.inflate(R.layout.item_game_console), onItemClick)
            else -> error("Incorrect viewType = $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SmartphoneHolder -> {
                val smartphone = electronics[position].let { it as? Electronics.Smartphone }
                    ?: error("Electronic at position = $position is not a smartphone")
                holder.bind(smartphone)
            }
            is TelevisionHolder -> {
                val television = electronics[position].let { it as? Electronics.Television }
                    ?: error("Electronic at position = $position is not a television")
                holder.bind(television)
            }
            is GameConsoleHolder -> {
                val gameConsole = electronics[position].let { it as? Electronics.GameConsole }
                    ?: error("Electronic at position = $position is not a game console")
                holder.bind(gameConsole)
            }
            else -> error("Incorrect view holder = $holder")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (electronics[position]) {
            is Electronics.Smartphone -> TYPE_SP
            is Electronics.Television -> TYPE_TV
            is Electronics.GameConsole -> TYPE_GC
        }
    }

    override fun getItemCount(): Int = electronics.size

    fun updateElectronics(newElectronics: List<Electronics>) {
        electronics = newElectronics
    }

    abstract class BaseElectronicsHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        private val electronicsImageView: ImageView = view.findViewById(R.id.electronicsImageView)
        private val countryTextView: TextView = view.findViewById(R.id.countryTextView)
        private val priceTextView: TextView = view.findViewById(R.id.priceTextView)
        private val screenSizeTextView: TextView = view.findViewById(R.id.screenSizeTextView)

        init {
            view.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        protected fun bindGeneralInfo(
            name: String,
            imageLink: String,
            screenSize: String,
            countryOfOrigin: String,
            price: Int
        ) {
            nameTextView.text = name
            screenSizeTextView.text = screenSize
            countryTextView.text =
                itemView.context.resources.getString(R.string.country_of_origin, countryOfOrigin)
            priceTextView.text = itemView.context.resources.getString(R.string.price, price)

            Glide.with(itemView)
                .load(imageLink)
                .placeholder(R.drawable.ic_baseline_photo)
                .error(R.drawable.ic_baseline_broken_image)
                .into(electronicsImageView)
        }
    }

    class SmartphoneHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : BaseElectronicsHolder(view, onItemClick) {
        private val osTextView: TextView = view.findViewById(R.id.osSPTextView)
        private val cameraTextView: TextView = view.findViewById(R.id.cameraSPTextView)
        private val processorTextView: TextView = view.findViewById(R.id.processorSPTextView)
        private val memoryTextView: TextView = view.findViewById(R.id.memorySPTextView)
        private val ramTextView: TextView = view.findViewById(R.id.ramSPTextView)
        private val batteryTextView: TextView = view.findViewById(R.id.batterySPTextView)

        fun bind(smartphone: Electronics.Smartphone) {
            bindGeneralInfo(
                name = smartphone.name,
                imageLink = smartphone.imageLink,
                screenSize = itemView.context.resources.getString(
                    R.string.screen_size,
                    smartphone.screenSize
                ),
                countryOfOrigin = smartphone.countryOfOrigin,
                price = smartphone.price
            )
            osTextView.text = itemView.context.resources.getString(R.string.os, smartphone.os)
            cameraTextView.text =
                itemView.context.resources.getString(R.string.camera, smartphone.camera)
            processorTextView.text =
                itemView.context.resources.getString(R.string.processor, smartphone.processor)
            memoryTextView.text =
                itemView.context.resources.getString(R.string.memory, smartphone.memory)
            ramTextView.text = itemView.context.resources.getString(R.string.ram, smartphone.ram)
            batteryTextView.text =
                itemView.context.resources.getString(R.string.battery, smartphone.battery)
        }
    }

    class TelevisionHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : BaseElectronicsHolder(view, onItemClick) {
        private val resolutionHDTextView: TextView = view.findViewById(R.id.resolutionHDTVTextView)
        private val isSmartTextView: TextView = view.findViewById(R.id.isSmartTVTextView)
        private val smartPlatformTextView: TextView =
            view.findViewById(R.id.smartPlatformTVTextView)

        fun bind(television: Electronics.Television) {
            bindGeneralInfo(
                name = television.name,
                imageLink = television.imageLink,
                screenSize = itemView.context.resources.getString(
                    R.string.screen_size,
                    television.screenSize
                ),
                countryOfOrigin = television.countryOfOrigin,
                price = television.price
            )

            resolutionHDTextView.text = itemView.context.resources.getString(
                R.string.hd_resolution,
                television.resolutionHD
            )
            if (television.isSmart) {
                isSmartTextView.text = itemView.context.resources.getString(R.string.smart_tv_true)
                smartPlatformTextView.isVisible = true
                smartPlatformTextView.text = itemView.context.resources.getString(
                    R.string.smart_os,
                    television.smartPlatform ?: "нет информации"
                )
            } else {
                isSmartTextView.text = itemView.context.resources.getString(R.string.smart_tv_false)
                smartPlatformTextView.isVisible = false
            }
        }
    }

    class GameConsoleHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : BaseElectronicsHolder(view, onItemClick) {
        private val processorTextView: TextView = view.findViewById(R.id.processorGCTextView)
        private val gProcessorTextView: TextView = view.findViewById(R.id.gProcessorGCTextView)
        private val memoryTextView: TextView = view.findViewById(R.id.memoryGCTextView)
        private val rayTracingTextView: TextView = view.findViewById(R.id.rayTracingGCTextView)
        private val ssdTextView: TextView = view.findViewById(R.id.ssdGCTextView)

        fun bind(gameConsole: Electronics.GameConsole) {
            val screenSizeOrResolution = if (gameConsole.screenSize != null) {
                itemView.context.resources.getString(R.string.screen_size, gameConsole.screenSize)
            } else {
                if (gameConsole.resolution != null) {
                    itemView.context.resources.getString(
                        R.string.hd_resolution,
                        gameConsole.resolution
                    )
                } else {
                    itemView.context.resources.getString(R.string.hd_resolution, "нет информации")
                }
            }

            bindGeneralInfo(
                name = gameConsole.name,
                imageLink = gameConsole.imageLink,
                screenSize = screenSizeOrResolution,
                countryOfOrigin = gameConsole.countryOfOrigin,
                price = gameConsole.price
            )

            processorTextView.text =
                itemView.context.resources.getString(R.string.processor, gameConsole.processor)
            gProcessorTextView.text = itemView.context.resources.getString(
                R.string.graphics_processor,
                gameConsole.gProcessor
            )
            memoryTextView.text =
                itemView.context.resources.getString(R.string.memory, gameConsole.memory)
            rayTracingTextView.text =
                if (gameConsole.rayTracing) {
                    itemView.context.resources.getString(R.string.ray_tracing_true)
                } else itemView.context.resources.getString(R.string.ray_tracing_false)
            ssdTextView.text =
                if (gameConsole.ssd) {
                    itemView.context.resources.getString(R.string.ssd_true)
                } else itemView.context.resources.getString(R.string.ssd_false)
        }
    }

    companion object {
        private const val TYPE_SP = 0
        private const val TYPE_TV = 1
        private const val TYPE_GC = 2
    }
}

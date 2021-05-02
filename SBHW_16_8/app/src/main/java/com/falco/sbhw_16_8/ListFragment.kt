package com.falco.sbhw_16_8

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.falco.sbhw_16_8.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private var electronics: List<Electronics> = listOf(
        Electronics.Smartphone(
            name = "Apple iPhone 12 Pro Max",
            imageLink = "https://avatars.mds.yandex.net/get-mpic/4393885/img_id6062364031316001810.png/orig",
            screenSize = "6.7\"(2778x1284)",
            countryOfOrigin = "Китай",
            price = 129000,
            processor = "Apple A14 Bionic",
            memory = 512,
            os = "iOS 14",
            ram = 6,
            camera = "12 МП F/1.60",
            battery = 3867
        ),
        Electronics.Smartphone(
            name = "Google Pixel 5",
            imageLink = "https://avatars.mds.yandex.net/get-marketpic/1587933/market_i6iOZaVLIQr8L9nkq7CtBQ/orig",
            screenSize = "6\"(2340x1080)",
            countryOfOrigin = "Китай",
            price = 56000,
            processor = "Qualcomm Snapdragon 765G 5G",
            memory = 128,
            os = "Android 11",
            ram = 8,
            camera = "12.20 МП F/1.70",
            battery = 4000
        ),
        Electronics.Smartphone(
            name = "Samsung Galaxy A52",
            imageLink = "https://avatars.mds.yandex.net/get-mpic/3614670/img_id672750693953131298.jpeg/orig",
            screenSize = "6.5\"(2400x1080)",
            countryOfOrigin = "Вьетнам",
            price = 25869,
            processor = "Qualcomm Snapdragon 720G",
            memory = 128,
            os = "Android 11",
            ram = 4,
            camera = "64 МП F/1.80",
            battery = 4500
        ),
        Electronics.Television(
            name = "Samsung UE43TU8000U",
            imageLink = "https://avatars.mds.yandex.net/get-mpic/4737085/img_id2463681284389328305.jpeg/orig",
            screenSize = "43\"(3840x2160)",
            countryOfOrigin = "Россия",
            price = 35499,
            isSmart = true,
            smartPlatform = "Tizen",
            resolutionHD = "4K UHD, HDR"
        ),
        Electronics.Television(
            name = "OLED LG OLED55CXR",
            imageLink = "https://avatars.mds.yandex.net/get-mpic/4322107/img_id6988048364160343090.jpeg/orig",
            screenSize = "55\"(3840x2160)",
            countryOfOrigin = "Россия",
            price = 106299,
            isSmart = true,
            smartPlatform = "webOS",
            resolutionHD = "4K UHD, HDR"
        ),
        Electronics.Television(
            name = "Sony KDL-40RE353",
            imageLink = "https://avatars.mds.yandex.net/get-mpic/4408567/img_id9105227582749725614.jpeg/orig",
            screenSize = "40\"(1920x1080)",
            countryOfOrigin = "Малайзия",
            price = 25999,
            isSmart = false,
            smartPlatform = null,
            resolutionHD = "Full HD"
        ),
        Electronics.GameConsole(
            name = "Sony PlayStation 5",
            imageLink = "https://avatars.mds.yandex.net/get-mpic/4080439/img_id3396893556724706605.jpeg/orig",
            screenSize = null,
            resolution = "4K 120Hz, 8K",
            countryOfOrigin = "Китай",
            price = 67990,
            processor = "AMD Ryzen Zen 2",
            memory = 825,
            rayTracing = true,
            gProcessor = "AMD Custom RDNA2",
            ssd = true
        ),
        Electronics.GameConsole(
            name = "Microsoft Xbox Series X",
            imageLink = "https://avatars.mds.yandex.net/get-mpic/1865885/img_id4720101346504429324.jpeg/13hq",
            screenSize = null,
            resolution = "4K 120Hz, 8K",
            countryOfOrigin = "Китай",
            price = 56980,
            processor = "AMD Ryzen Zen 2",
            memory = 1024,
            rayTracing = true,
            gProcessor = "AMD Custom RDNA2",
            ssd = true
        ),
        Electronics.GameConsole(
            name = "Nintendo Switch",
            imageLink = "https://avatars.mds.yandex.net/get-mpic/1374520/img_id2362682106774920179.jpeg/13hq",
            screenSize = "6.2\"(1280x720)",
            resolution = null,
            countryOfOrigin = "Китай",
            price = 22989,
            processor = "NVIDIA Tegra X1",
            memory = 32,
            rayTracing = false,
            gProcessor = "ARM Cortex A57",
            ssd = false
        )
    )

    private var electronicsAdapter: ElectronicsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setFragmentResultListener(KEY_REQUEST_TYPE) { key, bundle ->
//            Log.e(
//                "lifeCycle",
//                "setListener request = $KEY_REQUEST_TYPE, key = $key, bundle = $bundle, ${
//                    bundle.getString(KEY_BUNDLE_TYPE)
//                }"
//            )
//            when (val electronicType = bundle.getString(KEY_BUNDLE_TYPE)) {
//                TYPE_SP -> {
//                    SmartphoneInfoDialogFragment()
//                        .show(childFragmentManager, DIALOG_ADD_SMARTPHONE_TAG)
//                    addElectronicsResultListener()
//                }
//                TYPE_TV -> {
//                    TelevisionInfoDialogFragment()
//                        .show(childFragmentManager, DIALOG_ADD_TELEVISION_TAG)
//                    addElectronicsResultListener()
//                }
//                TYPE_GC -> {
//                    GameConsoleInfoDialogFragment()
//                        .show(childFragmentManager, DIALOG_ADD_GAME_CONSOLE_TAG)
//                    addElectronicsResultListener()
//                }
//                else -> error("Electronic type = $electronicType is not a SP, TV, GC ")
//            }
//        }

        childFragmentManager.setFragmentResultListener(KEY_REQUEST_TYPE, this) { key, bundle ->
            Log.d("myKey", key)
            when (val electronicType = bundle.getString(KEY_BUNDLE_TYPE)) {
                TYPE_SP -> {
                    SmartphoneInfoDialogFragment()
                        .show(childFragmentManager, DIALOG_ADD_SMARTPHONE_TAG)
                    addElectronicsResultListener()
                }
                TYPE_TV -> {
                    TelevisionInfoDialogFragment()
                        .show(childFragmentManager, DIALOG_ADD_TELEVISION_TAG)
                    addElectronicsResultListener()
                }
                TYPE_GC -> {
                    GameConsoleInfoDialogFragment()
                        .show(childFragmentManager, DIALOG_ADD_GAME_CONSOLE_TAG)
                    addElectronicsResultListener()
                }
                else -> error("Electronic type = $electronicType is not a SP, TV, GC ")
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
        electronicsAdapter?.updateElectronics(electronics)
        electronicsAdapter?.notifyDataSetChanged()
        binding.addFab.setOnClickListener {
            ElectronicsDialogFragment().show(childFragmentManager, DIALOG_ADD_ELECTRONICS_TAG)
        }
    }

    private fun initList() {
        electronicsAdapter = ElectronicsAdapter { position -> deleteElectronics(position) }
        with(binding.electronicsList) {
            adapter = electronicsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun addElectronicsResultListener() {
        childFragmentManager.setFragmentResultListener(
            KEY_REQUEST_ELECTRONIC,
            { lifecycle }
        ) { key, bundle ->
            val electronic = bundle.getParcelable(KEY_BUNDLE_ELECTRONIC) as? Electronics
                ?: error("object from the bundle = $bundle under the key = $KEY_BUNDLE_ELECTRONIC is not electronics")
            addElectronics(electronic)
        }
    }

    private fun deleteElectronics(position: Int) {
        val visibility: Boolean
        if (electronics.isNotEmpty()) {
            electronics = electronics.filterIndexed { index, _ -> index != position }
            visibility = electronics.isEmpty()
            electronicsAdapter?.updateElectronics(electronics)
            electronicsAdapter?.notifyItemRemoved(position)
        } else {
            visibility = true
        }
        emptyListTextView(visibility)
    }

    private fun emptyListTextView(visibility: Boolean) {
        binding.emptyListTextView.isVisible = visibility
    }

    override fun onDestroyView() {
        electronicsAdapter = null
        _binding = null
        super.onDestroyView()
    }

    private fun addElectronics(electronic: Electronics) {
        if (electronics.isEmpty()) emptyListTextView(false)
        electronics = listOf(electronic) + electronics
        electronicsAdapter?.updateElectronics(electronics)
        electronicsAdapter?.notifyItemInserted(0)
        binding.electronicsList.scrollToPosition(0)
        childFragmentManager.findFragmentByTag(DIALOG_ADD_ELECTRONICS_TAG)
            ?.let { it as? ElectronicsDialogFragment }
            ?.dismiss()
    }

    companion object {
        const val KEY_BUNDLE_TYPE = "electronic type bundle"
        const val KEY_BUNDLE_ELECTRONIC = "electronic bundle"
        const val KEY_REQUEST_TYPE = "electronic type request"
        const val KEY_REQUEST_ELECTRONIC = "electronic request"
        const val TYPE_SP = "smartphone"
        const val TYPE_TV = "television"
        const val TYPE_GC = "game console"
        private const val DIALOG_ADD_ELECTRONICS_TAG = "ElectronicsDialogTag"
        private const val DIALOG_ADD_SMARTPHONE_TAG = "SmartphoneInfoDialogTag"
        private const val DIALOG_ADD_TELEVISION_TAG = "TelevisionInfoDialogTag"
        private const val DIALOG_ADD_GAME_CONSOLE_TAG = "GameConsoleInfoDialogTag"
    }
}

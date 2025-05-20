package com.example.londondestination

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _destinationList = MutableStateFlow<List<MyData>>(emptyList())
    val destinationList: StateFlow<List<MyData>> = _destinationList.asStateFlow()

    private val _selectedItem = MutableStateFlow<MyData?>(null)
    val selectedItem: StateFlow<MyData?> = _selectedItem.asStateFlow()

    init {
        val data = listOf(
            MyData(
                nama = "Natural History Museum",
                description = "Natural History Museum di London adalah destinasi wajib bagi pecinta sains, sejarah alam, dan keluarga dengan anak-anak. Museum ini memiliki lebih dari 80 juta spesimen, mencakup zoologi, paleontologi, botani, dan geologi. Daya tarik utama termasuk rangka dinosaurus, seperti Diplodocus dan model animatronik Tyrannosaurus Rex. Selain itu, pengunjung bisa melihat koleksi batuan langka, meteorit, serta kristal dan permata.\n" +
                        "\n" + "Di Earth Galleries, ada pameran interaktif tentang geologi bumi, gunung berapi, dan gempa bumi. Museum ini juga memiliki zona edukasi yang menarik untuk anak-anak. Dibangun dengan arsitektur Romanesque bergaya Victoria, bangunannya memukau dengan interior yang megah. Yang menarik, museum ini gratis untuk dikunjungi, memberikan pengalaman yang tak terlupakan bagi semua pengunjung.",
                descriptionsingkat = "Museum sejarah tentang alam",
                year = 1881,
                image = R.drawable.naturalhistorymuseum,
                link = "https://en.wikipedia.org/wiki/Natural_History_Museum,_London"
            ),
            MyData(
                nama = "London Eye",
                description = "London Eye adalah kincir raksasa setinggi 135 meter di tepi Sungai Thames, dan menjadi salah satu ikon paling terkenal di London. Dari dalam kapsul kacanya, pengunjung bisa menikmati panorama kota yang menakjubkan, termasuk pemandangan Big Ben, Gedung Parlemen, dan Sungai Thames. Pada hari cerah, jarak pandang bisa mencapai hampir 40 kilometer.\n" +
                        "\n" + "Waktu terbaik untuk naik adalah saat senja, ketika cahaya kota mulai menyala dan langit berwarna keemasan karena menciptakan suasana romantis dan tenang. Setiap putaran berlangsung sekitar 30 menit, memberikan cukup waktu untuk mengagumi pemandangan, mengambil foto, atau sekadar menikmati momen dari ketinggian. London Eye bukan hanya atraksi wisata, tetapi juga simbol kebanggaan kota yang terus memikat baik turis maupun warga lokal.",
                descriptionsingkat = "Komedi putar raksasa London",
                year = 2000,
                image = R.drawable.londoneye,
                link = "https://en.wikipedia.org/wiki/London_Eye"
            ),
            MyData(
                nama = "British Museum",
                description = "British Museum adalah destinasi luar biasa bagi pecinta sejarah dan arkeologi, dengan koleksi global yang mencakup ribuan tahun peradaban manusia. Begitu masuk, suasana elegan langsung membawa pengunjung seakan menjelajahi masa lalu dari mumi Mesir, patung Yunani, artefak Mesopotamia, hingga keramik Tiongkok dan seni Islam. Batu Rosetta adalah salah satu sorotan utama, bersama Patung Ramses II dan reruntuhan Kuil Parthenon yang ikonik.\n" +
                        "\n" + "Setiap koleksi disertai penjelasan informatif yang memudahkan pengunjung memahami konteks sejarahnya. Great Court, dengan atap kaca yang terang dan desain modern, menjadi pusat bangunan yang menawan dan nyaman untuk bersantai. British Museum bukan sekadar tempat melihat artefak, tapi juga ruang kontemplatif yang menghubungkan kita dengan jejak panjang umat manusia dan semuanya bisa dinikmati tanpa biaya masuk.",
                descriptionsingkat = "Museum koleksi dunia",
                year = 1753,
                image = R.drawable.british_museum,
                link = "https://en.wikipedia.org/wiki/British_Museum"
            ),
            MyData(
                nama = "Big Ben",
                description = "Big Ben adalah ikon tak tergantikan London, berdiri megah di samping Gedung Parlemen di tepi Sungai Thames. Meski banyak mengira namanya merujuk pada menaranya, Big Ben sebenarnya adalah lonceng besar seberat lebih dari 13 ton di dalam Elizabeth Tower adalah nama resmi menara tersebut, yang diberikan untuk menghormati Ratu Elizabeth II.\n" +
                        "\n" + "Dentang Big Ben punya makna emosional yang dalam, sering terdengar dalam momen penting seperti pergantian tahun atau peringatan nasional, dan bahkan disiarkan BBC sejak 1920-an. Arsitekturnya yang anggun menjadi latar favorit para wisatawan, baik saat disinari mentari pagi maupun diterangi lampu malam hari. Walau akses ke dalam menara terbatas, cukup berdiri di dekatnya sudah membuat pengunjung merasa terhubung dengan sejarah dan semangat kota London yang tak lekang waktu",
                descriptionsingkat = "Jam besar London",
                year = 1859,
                image = R.drawable.bigben,
                link = "https://en.wikipedia.org/wiki/Big_Ben"
            ),
            MyData(
                nama = "Buckingham Palace",
                description = "Buckingham Palace adalah simbol monarki Inggris yang berdiri megah di pusat London, berfungsi sebagai kediaman resmi Raja dan pusat berbagai acara kenegaraan. Dengan lebih dari 700 ruangan, istana ini mencerminkan kemewahan dan sejarah yang hidup, bahkan dari luar pagar hitamnya yang ikonik.\n" +
                        "\n" + "Salah satu atraksi utama adalah Upacara Pergantian Penjaga, prosesi tradisional dengan seragam merah dan musik marching band yang menarik ribuan wisatawan setiap harinya. Jika bendera kerajaan berkibar di atas istana, itu menandakan Raja sedang berada di dalam momen sederhana yang membuat banyak orang merasa lebih dekat dengan sejarah kerajaan.\n" +
                        "\n" + "Pada musim panas, beberapa ruang kenegaraan dibuka untuk umum, menampilkan interior menawan lengkap dengan kristal, lukisan klasik, dan kemegahan khas kerajaan. Meski banyak pengunjung hanya melihat dari luar, pesona dan wibawa istana ini menjadikannya salah satu destinasi paling ikonik di London.",
                descriptionsingkat = "Istana resmi Kerajaan Inggris",
                year = 1703,
                image = R.drawable.buckinghampalace,
                link = "https://en.wikipedia.org/wiki/Buckingham_Palace"
            ),
            MyData(
                nama = "Tower of London",
                description = "Tower of London adalah benteng bersejarah di tepi Sungai Thames yang menyimpan kisah dramatis tentang kekuasaan, pengkhianatan, dan warisan kerajaan Inggris. Dulu berfungsi sebagai penjara bagi bangsawan, termasuk Anne Boleyn yang dieksekusi di sana, tempat ini memancarkan nuansa mencekam sekaligus megah, terutama di lokasi-lokasi penting seperti halaman eksekusi.\n" +
                        "\n" + "Namun, sisi gelap itu berpadu dengan kemewahan karena di sinilah Permata Mahkota Inggris disimpan, termasuk mahkota dan tongkat kerajaan yang berkilau menakjubkan. Kontras antara sejarah kelam dan simbol kejayaan membuat kunjungan ke Tower of London terasa seperti menyusuri lorong waktu, menghadirkan pengalaman mendalam yang tak terlupakan.",
                descriptionsingkat = "Benteng mempunyai banyak sejarah",
                year = 1066,
                image = R.drawable.toweroflondon,
                link = "https://en.wikipedia.org/wiki/Tower_of_London"
            ),
            MyData(
                nama = "Warner Bros. Studio Tour London",
                description = "Warner Bros. Studio Tour London adalah destinasi impian bagi penggemar Harry Potter, menawarkan pengalaman imersif ke dunia sihir yang sebelumnya hanya bisa dilihat di layar. Begitu masuk, kamu akan dibawa ke set asli seperti Great Hall, Diagon Alley, dan Privet Drive dimana semuanya penuh detail yang membuatmu merasa benar-benar berada di dunia Hogwarts.\n" +
                        "\n" + "Selain menjelajahi lokasi ikonik, pengunjung juga bisa melihat properti film seperti Horcrux, kostum rumah-rumah Hogwarts, hingga proses pembuatan efek visual yang menghidupkan sihir di layar. Setiap sudut studio menyuguhkan keajaiban yang membuat kamu makin menghargai imajinasi dan kerja keras di balik film. Dan tentu saja, mencicipi Butterbeer jadi penutup manis dari kunjungan yang terasa seperti pulang ke dunia masa kecil yang penuh keajaiban.",
                descriptionsingkat = "Studio Harry Potter London",
                year = 2012,
                image = R.drawable.harrypotterstudio,
                link = "https://www.wbstudiotour.co.uk/"
            ),
            MyData(
                nama = "Hyde Park",
                description = "Hyde Park adalah oase hijau di tengah London yang menawarkan ketenangan dan ruang bebas bagi siapa saja, dari warga lokal hingga turis. Dengan luas lebih dari 140 hektar, taman ini menjadi tempat ideal untuk jogging, bersepeda, membaca buku, atau sekadar duduk santai di tepi danau Serpentine.\n" +
                        "\n" + "Suasananya santai dan cocok buat piknik, bermain, atau menikmati kopi di bawah rindangnya pepohonan. Salah satu sudut paling unik adalah Speaker’s Corner, simbol kebebasan berpendapat di mana siapa pun bisa berbicara di depan umum. Selain sebagai tempat pelarian dari hiruk pikuk kota, Hyde Park juga kerap menjadi lokasi konser besar dan festival, menjadikannya ruang publik yang dinamis dan menyatu dengan jiwa kota London.",
                descriptionsingkat = "Taman pusat kota",
                year = 1637,
                image = R.drawable.hydepark,
                link = "https://en.wikipedia.org/wiki/Hyde_Park,_London"
            ),
            MyData(
                nama = "The Sherlock Holmes Museum",
                description = "The Sherlock Holmes Museum di 221B Baker Street adalah surga bagi penggemar detektif legendaris ini, membawa pengunjung langsung ke dunia fiksi era Victoria yang terasa hidup. Interiornya merekonstruksi ruang kerja dan rumah Holmes secara detail—lengkap dengan perapian, kaca pembesar, dan barang-barang pribadi khas karakter dalam cerita.\n" +
                        "\n" + "Setiap sudut museum dirancang agar kamu seolah benar-benar berada di tengah kisah misteri bersama Holmes dan Watson. Lebih dari sekadar pameran, museum ini menawarkan pengalaman yang memuaskan rasa ingin tahu para penggemar dan pencinta cerita klasik.",
                descriptionsingkat = "Museum seorang detektif yang ikonik",
                year = 1990,
                image = R.drawable.thesherlockholmesmuseum,
                link = "https://www.sherlock-holmes.co.uk/"
            ),
            MyData(
                nama = "St Paul's Cathedral",
                description = "St Paul’s Cathedral adalah salah satu ikon arsitektur paling menakjubkan di London, dikenal dengan kubah raksasanya yang mendominasi cakrawala kota. Dari luar terlihat megah, tapi keindahan sejatinya baru benar-benar terasa saat kamu melangkah masuk—ruang dalamnya hening, agung, dan sarat nuansa spiritual. Langit-langit tinggi, kaca patri indah, dan cahaya alami menciptakan atmosfer yang membuat siapa pun terdiam dalam kekaguman.\n" +
                        "\n" + "Salah satu pengalaman paling tak terlupakan di sini adalah menaiki ratusan anak tangga menuju puncak kubah. Dari atas, kamu bisa menikmati panorama kota London yang luas dan penuh sejarah, dari Sungai Thames hingga gedung-gedung modern yang berdiri berdampingan dengan bangunan bersejarah. Tak heran, St Paul’s sering menjadi lokasi berbagai momen penting nasional, karena tempat ini bukan hanya gereja, tapi simbol kekuatan dan keindahan yang hidup modern di kota.",
                descriptionsingkat = "Katedral bersejarah di London",
                year = 1710,
                image = R.drawable.stpaulcathedral,
                link = "https://en.wikipedia.org/wiki/St_Paul%27s_Cathedral"
            ),
            MyData(
                nama = "Camden Market",
                description = "Camden Market adalah salah satu tempat paling unik dan penuh karakter di London, ideal bagi kamu yang suka berburu barang-barang tak biasa dan merasakan suasana kota yang dinamis. Pasar ini dipenuhi toko-toko kecil yang menjual pakaian vintage, aksesori handmade, dan berbagai barang nyentrik yang sering kali hanya ada satu di dunia. Suasananya ramai tapi seru, dengan musik jalanan, aroma makanan internasional, dan gaya busana pengunjung yang beragam.\n" +
                        "\n" + "Selain jadi pusat belanja, Camden juga merupakan ruang ekspresi subkultur alternatif seperti punk, goth, dan hippie—tempat di mana semua orang bisa tampil sesuai dirinya sendiri. Kalau lapar, pilihan kulinernya luar biasa banyak dan menggoda, dari makanan Asia, Latin, Timur Tengah, sampai fusion kreatif. Setiap kunjungan ke Camden terasa seperti eksplorasi baru bukan sekadar belanja, tapi pengalaman hidup kota London yang bebas, kreatif, dan penuh kejutan.",
                descriptionsingkat = "Pasar terbesar di London",
                year = 1974,
                image = R.drawable.camdenmarket,
                link = "https://en.wikipedia.org/wiki/Camden_Market"
            )

        )
        _destinationList.value = data
        Log.d("HomeViewModel", "List data berhasil dimuat sebanyak ${data.size} item")
    }

    fun onItemClicked(item: MyData) {
        _selectedItem.value = item
    }

    fun resetSelectedItem() {
        _selectedItem.value = null
    }
}

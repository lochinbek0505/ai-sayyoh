package uz.falconmobile.ai_obida.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.falconmobile.ai_obida.databinding.FragmentHomeBinding
import uz.falconmobile.ai_obida.models.locate_model

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val list = arrayListOf(
            locate_model(
                id = "Abulqosim",
                voice_uz = "abulqosim.mp3",
                voice_ru = "abulqosim_ru.mp3",
                voice_eng = "abulqosim_eng.mp3",
                voice_turk = "abulqosim_turk.mp3",
                name_uz = "Abulqosim madrasasi",
                name_eng = "Abulqosim Madrasah",
                name_turk = "Abulqosim Medresesi",
                name_ru = "Медресе Абулкосим",
                text_uz = "Abulqosim madrasasi Toshkent shahrida joylashgan me'moriy yodgorlik bo'lib, madrasa, masjid va xonaqohdan iborat. Binoning qurilishi 1920-yilda boshlangan. Abul Kasim ismli shaxs tomonidan qurilganligi sababli shunday nomlangan. Tarixiy ahamiyatga ega bo'lgan bu joyda 1865-yilda Tashkentning rus generali Chernyaev tomonidan bosib olinishi natijasida tinchlik shartnomasi imzolangan. 1919-yilda bino yopilib, keyinchalik o'yinchoq fabrikasi sifatida foydalanilgan. 1980-yillarda restavratsiya qilinib, 1987-yilda qayta ochilgan.",
                text_ru = "Медресе Абулкосим — архитектурный памятник в Ташкенте, включающий медресе, мечеть и ханака. Строительство началось в 1920 году. Названо в честь Абул Касима, который инициировал его строительство. В этом исторически значимом месте в 1865 году был подписан мирный договор после захвата Ташкента русским генералом Черняевым. В 1919 году здание было закрыто и позже использовалось как фабрика игрушек. В 1980-х годах отреставрировано и вновь открыто в 1987 году.",
                text_eng = "Abulqosim Madrasah is an architectural monument located in Tashkent, comprising a madrasah, mosque, and khanqah. Construction began in 1920 and it was named after Abul Kasim, who initiated its construction. This historically significant site witnessed the signing of a peace treaty in 1865 after Tashkent was captured by Russian General Chernyaev. The building was closed in 1919 and later used as a toy factory. It was restored in the 1980s and reopened in 1987.",
                text_turk = "Abulqosim Medresesi, Taşkent'te bulunan ve medrese, cami ve hankahı içeren bir mimari anıttır. İnşaatı 1920'de başlamış ve yapımını başlatan Abul Kasim'in adını almıştır. Tarihi öneme sahip bu mekânda, 1865'te Rus General Chernyaev'in Taşkent'i ele geçirmesinin ardından bir barış antlaşması imzalanmıştır. Bina 1919'da kapatılmış ve daha sonra oyuncak fabrikası olarak kullanılmıştır. 1980'lerde restore edilerek 1987'de yeniden açılmıştır."
            ),
            locate_model(
                voice_uz = "kokaldosh.mp3",
                voice_eng = "kokaldosh_en.mp3",
                voice_turk = "kokaldosh_turk.mp3",
                voice_ru = "kokaldosh_ru.mp3",
                id = "Ko'kaldosh",
                name_uz = "Koʻkaldosh madrasasi",
                name_eng = "Kukeldash Madrasah",
                name_turk = "Kukeldaş Medresesi",
                name_ru = "Медресе Кукельдаш",
                text_uz = "Koʻkaldosh madrasasi — Oʻzbekiston Respublikasi Toshkent shahrining tarixiy markazida, Chorsu maydonida joylashgan 2 qavatli madrasa inshooti. Koʻkaldosh madrasasining tashkil etilishi Shayboniylar davrining ikki mashhur shaxsi — Buxoro xonligining nufuzli amaldori Qulbobo Koʻkaldosh va Toshkent hokimi Darveshxonning nomlari bilan bogʻliqdir. Ayrim, asosan, ilmiy-ommabop nashrlarda madrasa XVI asrda Qulbobo Koʻkaldosh tomonidan bunyod etilgan deyilgan va boshqa, ayniqsa inqilobdan oldingi asarlarda u Darveshxon tashabbusi bilan qurilgani yozib qoldirilgan. Koʻkaldosh madrasasi Buxoro xonligi, Toshkent shahri oʻrta asr shahristonining janubiy chekkasida qurilgan, hozirgi koʻcha oʻrni handaq boʻlgan.",
                text_ru = "Медресе Кукельдаш — двухэтажное здание, расположенное в историческом центре Ташкента, на площади Чорсу. Строительство медресе связано с именами двух известных личностей эпохи Шейбанидов — влиятельного чиновника Бухарского ханства Кулбобо Кукельдаша и хокима Ташкента Дарвешхана. В некоторых научно-популярных изданиях утверждается, что медресе было построено в XVI веке Кулбобо Кукельдашем, в то время как в других, особенно дореволюционных источниках, упоминается инициатива Дарвешхана. Медресе Кукельдаш было возведено на южной окраине средневекового шахристана Ташкента, на месте бывшего рва.",
                text_eng = "Kukeldash Madrasah is a two-story building located in the historic center of Tashkent, at Chorsu Square. Its construction is associated with two prominent figures of the Shaybanid era — the influential official of the Bukhara Khanate, Kulbobo Kukeldash, and the governor of Tashkent, Darvesh Khan. Some popular scientific publications claim that the madrasah was built in the 16th century by Kulbobo Kukeldash, while other sources, especially pre-revolutionary ones, attribute its construction to Darvesh Khan. Kukeldash Madrasah was erected on the southern outskirts of medieval Tashkent's shahristan, on the site of a former moat.",
                text_turk = "Kukeldaş Medresesi, Taşkent'in tarihi merkezinde, Çorsu Meydanı'nda bulunan iki katlı bir yapıdır. İnşası, Şeybaniler döneminin iki önemli şahsiyeti — Buhara Hanlığı'nın nüfuzlu yetkilisi Kulbobo Kukeldaş ve Taşkent valisi Darvesh Han — ile ilişkilidir. Bazı popüler bilimsel yayınlarda medresenin 16. yüzyılda Kulbobo Kukeldaş tarafından inşa edildiği belirtilirken, diğer kaynaklar, özellikle devrim"
            ),
            locate_model(
                voice_uz = "baroqxon.mp3",
                voice_ru = "baroqxon_ru.mp3",
                voice_turk = "baroqxon_turk.mp3",
                voice_eng = "baroqxon_en.mp3",
                id = "Baroqxon",
                name_uz = "Baroqxon madrasasi",
                name_eng = "Barak Khan Madrasa",
                name_turk = "Barak Han Medresesi",
                name_ru = "Медресе Баракхана",
                text_uz = """Baroqxon madrasasi — Toshkentda qurilgan Madrasa (1531/32 — 16-asr 2-yarmi). Hovli atrofida hujralar va ichki tarafida peshtoq boʻlib, madrasaning gʻarbiy qismi birmuncha oldinga chiqqan. Poydevori toshdan, qalin devorlari turli hajmdagi pishiq gʻishtdan ishlangan. Baʼzi joylari sinchli. Baroqxon madrasasi oʻrnida dastlab 2 (katta va kichik) maqbara boʻlgan. Kichik maqbarada Baroqxon qabri bor. Uning toʻrt tomonidagi eshiklaridan uchtasi keyinroq berkitib tashlangan (qolgan eshikdan zina bilan tomga chiqilgan). 1904-yilda masjidga aylantirilgan katta maqbarada Toshkent hokimi Suyunchxoʻja qabri joylashgan. Baroqxon ikki maqbarani oʻz ichiga olgan Madrasa qurdirgan. Madrasaning sharq tomonidagi ulkan peshtoqqa rang-barang sirkor gʻishtchalardan namoyon terilgan. U handasiy va girih naqshlar bilan hoshiyalangan. Peshtoqning yuqori qismi saqlanmagan. Asosiy peshtoqning orqa tomonida yana peshtoq va besh qirrali ravoq bor. Gʻarb tomondagi ichki peshtoq koʻk gumbazning tarkibiy qismi boʻlga""",
                text_eng = """Barak Khan Madrasa — built in Tashkent in 1531/32 (second half of the 16th century). The courtyard is surrounded by cells (hujras), and there is an iwan at the center. The western part of the madrasa extends slightly forward. Its foundation is made of stone and the thick walls are built from baked bricks of various sizes. Initially, there were two mausoleums on the site—large and small. The smaller one contains the tomb of Barak Khan. Three of its four doors were later sealed (one remained, leading to the roof). In 1904, the larger mausoleum was turned into a mosque and contains the tomb of Tashkent governor Suyunchkhoja. The madrasa incorporates both mausoleums. The eastern grand iwan is adorned with colorful glazed bricks and bordered by geometric and girih patterns. The upper part of the iwan has not survived. Behind the main iwan is another arched structure with five facets. The inner western iwan forms part of the blue dome's structure.""",
                text_turk = """Barak Han Medresesi — Taşkent'te 1531/32 yıllarında (16. yüzyılın ikinci yarısı) inşa edilmiştir. Avlunun etrafında hücreler ve ortasında bir eyvan bulunur. Medresenin batı kısmı biraz öne çıkmıştır. Temeli taştan olup, kalın duvarları çeşitli boyutlardaki pişmiş tuğlalardan yapılmıştır. Başlangıçta burada biri büyük, biri küçük olmak üzere iki türbe vardı. Küçük türbede Barak Han’ın mezarı bulunur. Dört kapısından üçü sonradan kapatılmış, biri merdivenle çatıya çıkış olarak kalmıştır. 1904’te camiye çevrilen büyük türbede Taşkent valisi Süyünç Hoca’nın mezarı yer alır. Barak Han bu iki türbeyi içeren bir medrese yaptırmıştır. Doğu tarafındaki büyük eyvan, renkli sırlı tuğlalarla süslenmiş olup, geometrik ve girih motiflerle çevrilidir. Eyvanın üst kısmı günümüze ulaşmamıştır. Ana eyvanın arkasında başka bir eyvan ve beş köşeli bir kemer yer alır. Batıdaki iç eyvan mavi kubbenin yapısal parçasıdır.""",
                text_ru = """Медресе Баракхана — медресе, построенное в Ташкенте в 1531/32 годах (вторая половина XVI века). Внутренний двор окружён хижинами (худжрами), в центре находится пештак. Западная часть медресе немного выступает вперёд. Фундамент выполнен из камня, а толстые стены — из обожжённого кирпича различных размеров. Изначально на месте медресе находились два мавзолея — большой и малый. В малом мавзолее находится могила Баракхана. Из четырёх дверей три были позднее замурованы, а одна оставлена для выхода на крышу. В 1904 году большой мавзолей был превращён в мечеть, в нём похоронен ташкентский хоким Суюнчходжа. Медресе включает в себя оба мавзолея. Восточный пештак украшен разноцветными глазурованными кирпичами и обрамлён геометрическими и гирих-узорами. Верхняя часть пештака не сохранилась. За главным пештаком находится ещё один иван и пятигранная арка. Внутренний западный иван является частью конструкции голубого купола."""
            ),
            locate_model(
                voice_uz = "qaffol.mp3",
                id = "Qaffol",
                voice_turk = "qaffol_turk.mp3",
                voice_eng = "qaffol_en.mp3",
                voice_ru = "qaffol_ru.mp3",
                name_uz = "Qaffol Shoshiy maqbarasi",
                name_eng = "Mausoleum of Qaffol Shoshiy",
                name_turk = "Kaffal Şaşi Türbesi",
                name_ru = "Мавзолей Каффаль Шоши",
                text_uz = """Qaffol Shoshiy maqbarasi — Toshkent shahrining Olmazor tumanida joylashgan XVI asrga oid meʼmoriy yodgorlik.

Hozirgi kunda Qaffol Shoshiy maqbarasi Hazrati Imom majmuasi tarkibiga kiradi.
Hazrati Imom (Hastimom) nomi bilan mashhur boʻlgan imom Abu Bakr Muhammad ibn Ali Ismoil Qaffol ash-Shoshiyga atab, uning qabri ustiga qurilgan. Dastlabki maqbara saqlanmagan.

Qaffol Shoshiy maqbarasi meʼmor Gʻulom Husayn tomonidan 1541—1542-yillarda chorsi pishiq gʻishtdan bunyod etilgan.""",
                text_eng = """The Mausoleum of Qaffol Shoshiy is a 16th-century architectural monument located in the Olmazor district of Tashkent.

Today, the mausoleum is part of the Hazrati Imam Complex. It was built in honor of Imam Abu Bakr Muhammad ibn Ali Ismail Qaffol ash-Shoshiy, a renowned scholar known as Hazrati Imam (Hastimom), over his grave. The original mausoleum has not survived.

The existing structure was built between 1541 and 1542 by architect Ghulom Husayn using baked bricks in the form of a chorsu (cross-shaped) layout.""",
                text_turk = """Kaffal Şaşi Türbesi, Taşkent’in Olmazor bölgesinde yer alan 16. yüzyıla ait bir mimari eserdir.

Günümüzde türbe, Hazrati İmam Kompleksi’nin bir parçasıdır. Halk arasında Hastimom olarak bilinen büyük alim İmam Ebu Bekir Muhammed bin Ali İsmail Kaffal eş-Şaşi’nin mezarı üzerine inşa edilmiştir. İlk inşa edilen türbe günümüze ulaşmamıştır.

Mevcut türbe, 1541–1542 yılları arasında mimar Ghulom Hüseyin tarafından pişmiş tuğladan, dört kollu (çorsı) planla inşa edilmiştir.""",
                text_ru = """Мавзолей Каффаль Шоши — архитектурный памятник XVI века, расположенный в районе Олмазор города Ташкента.

В настоящее время мавзолей входит в состав комплекса Хазрати Имам. Он был построен над могилой известного богослова Имама Абу Бакра Мухаммада ибн Али Исмаила Каффаль аш-Шоши, более известного как Хазрати Имам (Хастимом). Первоначальный мавзолей не сохранился.

Существующее сооружение было построено в 1541–1542 годах архитектором Гуломом Хусаином из обожжённого кирпича в форме чарсы (крестообразной планировки)."""
            ),
            locate_model(
                voice_uz = "qaldirgochbiy.mp3",
                voice_ru = "qaldirgochbiy_ru.mp3",
                voice_eng = "qaldirgochbiy_en.mp3",
                voice_turk = "qaldirgochbiy_turk.mp3",
                id = "Qaldirgʻochbiy",
                name_uz = "Qaldirgʻochbiy maqbarasi",
                name_eng = "Mausoleum of Qaldirgʻochbiy",
                name_turk = "Kaldırgachbiy Türbesi",
                name_ru = "Мавзолей Калдыргачбий",
                text_uz = """Qaldirgʻochbiy maqbarasi, Hazrati Amir maqbarasi — Toshkent shahridagi meʼmoriy yodgorlik (15-asr 1-yarmi). Shayx Xovandi Tohur majmuasi tarkibida, koʻhna qabristonning shimolida joylashgan.

Maqbara pishiq gʻishtdan (25×25×5 sm) chortoq tarhli (9,48×9,48 m), bir xonali (tarhi 6×6 m), qoʻsh qavat gumbazli qilib qurilgan; 12 qirrali tashqi kulohsimon choʻqqi gumbazi pishiq gʻishtdan ganch qorishmasida terilgan.

Maqbara ichiga janub tomondagi eshik orqali kiriladi, xonadagi tepaga chiqiladigan aylana zinalar qalin devor ichiga joylashgan; xona oʻrtasida sagʻana bor, tagxona ichidan bir necha jasad qoldiqlari topilgan.

Maqbara bezaklaridan 15-asrga mansub sharafalarning qoldiqlarigina saqlangan. 1970-yilda tashqi gumbazi taʼmirlangan.""",
                text_eng = """The Mausoleum of Qaldirgʻochbiy (also known as the Mausoleum of Hazrati Amir) is a 15th-century architectural monument in Tashkent. It is located in the northern part of the ancient cemetery within the Sheikh Khavandi Tahur complex.

Built from baked bricks (25×25×5 cm), the mausoleum has a chortaq (square) layout (9.48×9.48 m), consists of a single room (6×6 m), and features a two-tiered dome. The outer conical dome with 12 facets was constructed from bricks bonded with a gypsum mixture.

Entry is through a southern door. A spiral staircase embedded within the thick walls leads to the upper level. A cenotaph stands in the center of the room, and remains of several bodies were discovered in the crypt below.

Only fragments of 15th-century decorative inscriptions have survived. The outer dome was restored in 1970.""",
                text_turk = """Kaldırgachbiy Türbesi (diğer adıyla Hazreti Emir Türbesi), Taşkent’te bulunan 15. yüzyılın ilk yarısına ait mimari bir yapıdır. Şeyh Hovendi Tahur kompleksi içinde, eski mezarlığın kuzey kısmında yer alır.

Pişmiş tuğladan (25×25×5 cm) yapılmış türbe, 9,48×9,48 m boyutlarında dörtgen planlı, tek odalı (6×6 m), iki katlı kubbeye sahiptir. 12 köşeli konik dış kubbesi alçı harçla örülmüş pişmiş tuğlalardan yapılmıştır.

Giriş güney tarafındaki kapıdandır. Kalın duvarlar içinde spiral bir merdiven üst kata çıkar. Odanın ortasında bir sanduka bulunur, alt kattaki odadan birkaç insan kalıntısı çıkarılmıştır.

15. yüzyıla ait süslemelerden yalnızca bazı parçalar günümüze ulaşmıştır. Dış kubbesi 1970 yılında restore edilmiştir.""",
                text_ru = """Мавзолей Калдыргачбий (также известный как мавзолей Хазрати Амира) — архитектурный памятник первой половины XV века в Ташкенте. Расположен в северной части древнего кладбища, в составе комплекса Шейха Хованди Тахура.

Мавзолей построен из обожжённого кирпича (25×25×5 см), имеет форму чартака (9,48×9,48 м), одну комнату (6×6 м) и двухъярусный купол. Наружный конический купол с 12 гранями выложен из кирпича с гипсовым раствором.

Вход находится с южной стороны. Внутри толстых стен расположена винтовая лестница, ведущая на верх. В центре комнаты находится саган, а в подвале были найдены останки нескольких тел.

От декора сохранились лишь фрагменты надписей XV века. Наружный купол был отреставрирован в 1970 году."""
            ),
            locate_model(
                voice_uz = "shayxontohur.mp3",
                voice_eng = "shayxontohur_en.mp3",
                voice_turk = "shayxontohur_turk.mp3",
                voice_ru = "shayxontohur_ru.mp3",
                id = "Shayxontohur",
                name_uz = "Shayxontohur mozori",
                name_eng = "Mausoleum of Shaykhontohur",
                name_turk = "Şeyhantohur Türbesi",
                name_ru = "Мавзолей Шайхантаура",
                text_uz = """Shayxontohur mozori (maqbarasi) 14-asrda qurilgan, ammo uning tashqi koʻrinishi yillar davomida qayta-qayta oʻzgargan. Maqbara qabristoni yonida noyob muqaddas Iskandar Sauri joylashgan. Saur – ignabargli daraxtlarning mahalliy navi boʻlib, u XV asrda butunlay yoʻq boʻlib ketgan. Ularning kelib chiqishi Sharqda afsonaviy qahramon sifatida keng hurmatga sazovor boʻlgan Makedoniyalik Iskandar nomi bilan bogʻliq deb ishoniladi.

Bu joy Shayxontavrning dafn qilinadigan joyi sifatida tanlangan boʻlishi mumkin, chunki bu yerda ushbu ajoyib Savrlar mavjud. Maqbaraning oʻlchamlari: kengligi 16,2 x 9 m, balandligi 12,8 m.""",
                text_eng = """The Shaykhontohur Mausoleum was built in the 14th century, though its exterior has been repeatedly altered over time. Near the mausoleum is a unique sacred tree known as the Iskandar Saur. The Saur is a local species of coniferous tree that became extinct in the 15th century. It is believed that these trees are associated with Alexander the Great, a legendary figure greatly revered in the East.

The presence of these rare Saur trees might be the reason why this location was chosen as the burial site for Shaykhontohur. The dimensions of the mausoleum are 16.2 x 9 meters in width and 12.8 meters in height.""",
                text_turk = """Şeyhantohur Türbesi 14. yüzyılda inşa edilmiştir, ancak dış görünüşü zamanla birçok kez değiştirilmiştir. Türbenin yakınında nadir bulunan kutsal İskender Saur ağacı yer almaktadır. Saur, 15. yüzyılda tamamen yok olmuş olan yerli bir iğne yapraklı ağaç türüdür. Bu ağaçların, Doğu'da efsanevi bir kahraman olarak saygı gören Büyük İskender ile bağlantılı olduğuna inanılmaktadır.

Bu nadir Saur ağaçlarının bulunduğu yer olması, Şeyhantohur'un burada defnedilmesi için seçilmesinin sebebi olabilir. Türbenin ölçüleri: genişlik 16,2 x 9 m, yükseklik 12,8 m.""",
                text_ru = """Мавзолей Шайхантаура был построен в XIV веке, однако его внешний вид неоднократно изменялся на протяжении веков. Рядом с мавзолеем находится редкое священное дерево — Искандар Саур. Саур — это местный вид хвойного дерева, который полностью исчез в XV веке. Считается, что их происхождение связано с Александром Македонским, широко почитаемым в Востоке как легендарный герой.

Наличие этих редких деревьев могло стать причиной выбора этого места для захоронения Шайхантаура. Размеры мавзолея: ширина 16,2 x 9 м, высота 12,8 м."""
            ),
            locate_model(
                voice_uz = "shayxontohur_darvozasi.mp3",
                voice_ru = "shayxontohur_darvozasi_ru.mp3",
                voice_eng = "shayxontohur_darvozasi_en.mp3",
                voice_turk = "shayxontohur_darvozasi_turk.mp3",
                id = "Shayxontohur darvozasi",
                name_uz = "Shayxontohur darvozasi",
                name_eng = "Shaykhontohur Complex Gate",
                name_turk = "Şeyhantohur Ziyaret Kompleksi Kapısı",
                name_ru = "Ворота комплекса Шайхантаура",
                text_uz = """Shayxontohur ziyoratgohi Toshkent shahrining Abdulla Qodiriy va Alisher Navoiy ko‘chalari kesishmasida joylashgan. Ziyoratgoh uchta maqbaradan iborat bo‘lib, bular: Shayx Xovand At-Tohur maqbarasi, Qaldirg‘ochbiy maqbarasi va Yunusxon maqbarasidir. Ushbu kompleks o‘zining tarixiy, diniy va me’moriy ahamiyati bilan mashhur bo‘lib, ko‘plab mahalliy va xorijiy ziyoratchilarni jalb qiladi.""",
                text_eng = """The Shaykhontohur pilgrimage site is located at the intersection of Abdulla Qodiriy and Alisher Navoi streets in Tashkent. The complex consists of three mausoleums: the Mausoleum of Shaykh Khavandi At-Tokhur, the Mausoleum of Qaldirg‘ochbiy, and the Mausoleum of Yunus Khan. This site is renowned for its historical, religious, and architectural significance, attracting many local and foreign visitors.""",
                text_turk = """Şeyhantohur ziyaret kompleksi, Taşkent şehrinde Abdulla Qodiriy ve Alişir Nevai caddelerinin kesişiminde yer almaktadır. Kompleks üç türbeden oluşur: Şeyh Havendi Tohur Türbesi, Kaldırgoçbiya Türbesi ve Yunus Han Türbesi. Bu kompleks, tarihi, dini ve mimari önemiyle tanınmakta ve birçok yerli ve yabancı ziyaretçiyi kendine çekmektedir.""",
                text_ru = """Зиярат Шайхантаура расположен на пересечении улиц Абдуллы Кадыри и Алишера Навои в Ташкенте. Комплекс состоит из трех мавзолеев: мавзолея шейха Хованди Ат-Тахура, мавзолея Калдыргоч-бия и мавзолея Юнусхана. Этот объект славится своим историческим, религиозным и архитектурным значением и привлекает множество местных и зарубежных паломников."""
            ),
            locate_model(
                voice_uz = "suzuk_ota.mp3",
                voice_turk = "suzuk_ota_turk.mp3",
                voice_eng = "suzuk_ota_en.mp3",
                voice_ru = "suzuk_ota_ru.mp3",
                id = "Suzuk ota",
                name_uz = "Suzuk ota masjidi",
                name_eng = "Suzuk Ota Mosque",
                name_turk = "Suzuk Ata Camii",
                name_ru = "Мечеть Сузук-ота",
                text_uz = """Suzuk ota masjidi – Oʻzbekiston Respublikasining Toshkent shahri Shayxontohur tumanida, Chorsu bozoridan unchalik uzoq boʻlmagan Suzuk ota koʻchasida joylashgan masjid.

Masjidning barpo etilishi dastlab Amir Temur davriga borib taqaladi. Amir Temur 1363-yildagi yurishi davrida betob boʻlib, 6 oy davomida Toshkentda davolangan. Uning buyrugʻiga binoan 1363–1364-yillarda Xoja Ahmad Yassaviyning nabirasi Suzuk ota qabri ustida maqbara va jomeʼ masjid qurilgan. Hozirgi majmuaga muzey, avtoturargoh, doʻkonlar, kutubxona, maqbara va masjid kiradi. Kirish qismidagi ikki tomonlama yoʻl bo‘yida hunarmandlar uchun 34 ta ikki qavatli uy-joy mavjud.""",
                text_eng = """Suzuk Ota Mosque is located on Suzuk Ota Street in the Shaykhontohur district of Tashkent, Uzbekistan, not far from Chorsu Bazaar.

The origins of the mosque date back to the time of Amir Temur. During his 1363 campaign, he fell ill and had to remain in Tashkent for six months for treatment. By his order, a mausoleum and congregational mosque were built in 1363–1364 over the grave of Suzuk Ota, a grandson of Khoja Ahmad Yassawi. The complex includes a museum, parking, shops, a library, the Suzuk Ota mausoleum, and the mosque. Additionally, 34 two-story houses for craftsmen were built along the entrance path.""",
                text_turk = """Suzuk Ata Camii, Özbekistan'ın Taşkent şehri Şeyhantohur ilçesinde, Çorsu Pazarı'na çok uzak olmayan Suzuk Ata Caddesi'nde yer almaktadır.

Caminin inşası ilk olarak Emir Timur dönemine kadar uzanır. Emir Timur, 1363 seferi sırasında hastalanmış ve altı ay boyunca Taşkent'te tedavi edilmiştir. Emir Timur’un emriyle, Hoca Ahmet Yesevi'nin torunu olan Suzuk Ata'nın kabri üzerine 1363–1364 yıllarında bir türbe ve cami inşa edilmiştir. Günümüzdeki komplekste müze, otopark, dükkanlar, kütüphane, türbe ve cami yer alır. Giriş yolunun iki yanında zanaatkârlar için 34 adet iki katlı ev inşa edilmiştir.""",
                text_ru = """Мечеть Сузук-ота расположена в Шайхантаурском районе города Ташкента, недалеко от базара Чорсу, на улице Сузук-ота.

Появление мечети относится ко времени Амир Темура. Во время своего похода в 1363 году он заболел и был вынужден лечиться в Ташкенте в течение шести месяцев. По его указу в 1363–1364 годах над могилой Сузук-оты, внука Ходжи Ахмада Ясави, были построены мавзолей и соборная мечеть. В состав современного комплекса входят музей, автостоянка, магазины, библиотека, мавзолей Сузук-оты и сама мечеть. У входа вдоль дороги построены 34 двухэтажных дома для ремесленников."""
            ),
            locate_model(
                voice_uz = "yunusxon.mp3",
                voice_eng = "yunusxon_en.mp3",
                voice_ru = "yunusxon_ru.mp3",
                voice_turk = "yunusxon_turk.mp3",
                id = "Yunusxon",
                name_uz = "Yunusxon maqbarasi",
                name_eng = "Yunus Khan Mausoleum",
                name_turk = "Yunus Han Türbesi",
                name_ru = "Мавзолей Юнусхана",
                text_uz = """Yunusxon maqbarasi – Toshkentdagi meʼmoriy yodgorlik (15-asr); Oʻzbekiston xalqaro islom akademiyasi hududida joylashgan. 1487-yilda Toshkent hokimi Yunusxon vafotidan soʻng Shayx Xovandi Tohur qabristonida Yunusxonning oʻgʻli Ahmadxon (Olachaxon) tomonidan qurdirilgan deb taxmin etiladi, qurilish keyingi davrlarda ham davom ettirilgan.

Maqbara toʻrtburchak tarhli (21,2×22 m), peshtoqgumbazli, katta xona (7×7 m), ikki yonida qoʻsh qavatli hujralardan iborat. Binoning tashqi gumbazi, peshtoq ravogʻi (19-asrda) buzilib ketgan, bezaklari saqlanmagan, bino bir necha marta taʼmirlangan. Xonaqohning ichki qismiga ganchkori naqshlar, muqarnaslar ishlangan. 1970-yillarning oxirida Yunusxon maqbarasining vayron boʻlgan qismlari tiklandi; 1999—2005-yillarda qattiq taʼmirlandi.""",
                text_eng = """The Yunus Khan Mausoleum is an architectural monument in Tashkent dating back to the 15th century. It is located within the territory of the International Islamic Academy of Uzbekistan. It is believed that the mausoleum was built by Yunus Khan's son, Ahmad Khan (also known as Olacha Khan), after Yunus Khan, the ruler of Tashkent, died in 1487. Construction is thought to have continued in subsequent periods.

The mausoleum has a rectangular layout (21.2×22 m), a portal-dome design, and a large central room (7×7 m), with two-story chambers on either side. The outer dome and the portal arch were destroyed in the 19th century, and decorations have not been preserved. The building has undergone several restorations. The interior features intricate gypsum carvings and muqarnas. Major reconstruction took place in the late 1970s, and full restoration was carried out between 1999 and 2005.""",
                text_turk = """Yunus Han Türbesi, Taşkent'te bulunan 15. yüzyıla ait mimari bir anıttır. Özbekistan Uluslararası İslam Akademisi arazisinde yer almaktadır. 1487 yılında Taşkent hakimi Yunus Han’ın vefatından sonra oğlu Ahmed Han (Olaca Han) tarafından Şeyh Hovendi Tahur mezarlığında yaptırıldığı tahmin edilmektedir. İnşaat daha sonraki dönemlerde de devam etmiştir.

Türbe dikdörtgen planlıdır (21,2×22 m), taçkapılı ve kubbeli bir yapıdır; büyük bir oda (7×7 m) ve iki yanında iki katlı hücreler yer almaktadır. Binanın dış kubbesi ve taçkapı kemeri 19. yüzyılda yıkılmış, süslemeleri korunamamıştır. Bina birkaç kez onarılmıştır. İç kısmı alçı süslemeler ve mukarnaslarla bezemelidir. 1970'lerin sonunda harap olan bölümleri yeniden inşa edilmiş, 1999–2005 yılları arasında kapsamlı şekilde restore edilmiştir.""",
                text_ru = """Мавзолей Юнусхана — архитектурный памятник XV века в Ташкенте. Расположен на территории Международной исламской академии Узбекистана. Считается, что мавзолей был построен сыном Юнусхана Ахмадханом (Олача-ханом) после смерти Юнусхана, правителя Ташкента, в 1487 году. Строительство продолжалось и в последующие периоды.

Мавзолей имеет прямоугольную форму (21,2×22 м), портально-купольную конструкцию и большую комнату (7×7 м), по бокам которой расположены двухэтажные кельи. В XIX веке внешний купол и арка портала были разрушены, декоративная отделка не сохранилась. Здание многократно реставрировалось. Внутри — резьба по гипсу и мукарнасы. В конце 1970-х годов были восстановлены разрушенные части мавзолея; в 1999–2005 годах проведена капитальная реставрация."""
            ),
        )




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
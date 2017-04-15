package com.house603.cash.feature;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.house603.cash.R;
import com.house603.cash.feature.adapter.CountryListAdapter;
import com.house603.cash.feature.adapter.CountryListAdapterListener;
import com.house603.cash.feature.model.CurrencyModel;

import java.util.ArrayList;
import java.util.List;

public class CountryListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    CurrencyModel mCurrencymodel;
    private LinearLayoutManager mLayoutManager;
    CountryListAdapter mAdapter;
    private List<CurrencyModel> mCountryList;
    private int id_flag1;
    int id;
    String[] currencyNames = new String[]{
            "Emirati Dirham","Afghan Afghani","Albanian Lek","Armenian Dram",
            "Netherlands Antillean Guilder","Angolan Kwanza", "Argentine Peso","Australian Dollar",
            "Aruban Florina","Azerbaijani Manat","Bosnian Marka","Barbadian Dollar",
            "Bangladeshi Taka","Bulgarian Lev","Bahraini Dinar","Burundian Franc","Bermudan Dollar",
            "Brunei Dollar","Bolivian Boliviano","Brazilian Real","Bahamian Dollar","Bitcoin",
            "Bhutanese Ngultrum","Botswanan Pula","Belarusian Ruble", "Belize Dollar",
            "Canadian Dollar","Congo Franc","DR Congo Franc","Swiss Franc","Chilean Peso",
            "Chilean Unidad de Fomento","Chinese Yuan","Colombian Peso","Costa Rican Colón",
            "Cuban Convertible Peso","Cuba Peso","Cape Verdean Escudo","Czech Republic Koruna",
            "Djiboutian Franc","Danish Krone","Dominican Peso","Algerian Dinar","Egyptian Pound",
            "Eritrean Nakfa","Ethiopian Birr","Euro","Fijian Dollar","Falkland Islands Pound",
            "British Pound","Georgian Lari","Guernsey Pound","Ghanaian Cedi","Gibraltar Pound",
            "Gambian Dalasi","Guinean Franc","Guatemalan Quetzal","Guyanaese Dollar",
            "Hong Kong Dollar","Honduran Lempira","Croatian Kuna","Haitian Gourde",
            "Hungarian Forint","Indonesian Rupiah","Israeli New Shekel","Isle of Man Pound",
            "Indian Rupee","Iraqi Dinar","Iranian Rial","Icelandic Króna","Jersey Pound",
            "Jamaican Dollar","Jordanian Dinar","Japanese Yen","Kenyan Shilling","Kyrgystani Som",
            "Cambodian Riel","Comorian Franc","North Korean Won","South Korean Won","Kuwaiti Dinar",
            "Cayman Islands Dollar","Kazakhstani Tenge","Laotian Kip","Lebanese Pound",
            "Sri Lankan Rupee","Liberian Dollar","Lesotho Loti","Libyan Dinar","Morrocan Dirham",
            "Moldovan Leu","Malagasy Ariary","Macedonian Denar","Myanmar Kyat","Mongolian Tugrik",
            "Macau Pataca","Mauritanian Ouguiya","Mauritian Rupee","Maldivian Rufiyaa",
            "Malawian Kwacha","Mexican Peso","Malaysian Ringgit","Mozambican Metical",
            "Namibian Dollar","Nigerian Naira","Nicaraguan Córdoba","Norwegian Krone",
            "Nepalese Rupee","New Zealand Dollar","Omani Rial","Panamanian Balboa",
            "Peruvian Sol","Papua New Guinean Kina","Philippine Peso","Pakistani Rupee",
            "Polish Zloty","Paraguayan Guarani","Qatari Rial","Romanian Leu","Serbian Dinar",
            "Russian Ruble","Rwandan Franc","Saudi Riyal","Solomon Islands Dollar",
            "Seychellois Rupee","Sudanese Pound","Swedish Krona","Singapore Dollar",
            "St. Helena Pound","Sierra Leonean Leone","Somali Shilling","Surinamese Dollar",
            "South Sudanese Pound","São Tomé & Príncipe Dobra","Salvadoran Colón","Syrian Pound",
            "Swazi Lilangeni","Thai Baht","Tajikistani Somoni","Turkmenistani Manat","Tunisian Dinar",
            "Tongan Paʻanga","Turkish Lira","Trinidad & Tobago Dollar","New Taiwan Dollar",
            "Tanzanian Shilling","Ukrainian Hryvnia","Ugandan Shilling","United States Dollar",
            "Uruguayan Peso","Uzbekistani Som","Venezuelan Bolívar","Vietenamese Dong","Vanuatu Vatu",
            "Samoan Tala","Central African CFA Franc","East Caribbean Dollar", "West African CFA Franc",
            "CFP Franc","Yemeni Rial","South African Rand","Zambian Kwacha","Zimbabwean dollar"


    };
    int[] flagId = new int[]{
            R.mipmap.united_arab_emirates, R.mipmap.afghanistan, R.mipmap.albania, R.mipmap.armenia,
            R.mipmap.netherlands, R.mipmap.angola, R.mipmap.argentina, R.mipmap.australia,
            R.mipmap.aruba, R.mipmap.azerbaijan, R.mipmap.bosnian, R.mipmap.barbados,
            R.mipmap.bangladesh, R.mipmap.bulgaria, R.mipmap.bahrain, R.mipmap.burundi,
            R.mipmap.bermuda, R.mipmap.brunei, R.mipmap.bolivia, R.mipmap.brazil, R.mipmap.bahamas,
            R.mipmap.bitcoin, R.mipmap.bhutan, R.mipmap.botswana, R.mipmap.belarus, R.mipmap.belize,
            R.mipmap.canada, R.mipmap.congo, R.mipmap.congo_kinshasa, R.mipmap.switzerland, R.mipmap.chile,
            R.mipmap.chile, R.mipmap.china, R.mipmap.colombia, R.mipmap.costa_rica, R.mipmap.cuba,
            R.mipmap.cuba, R.mipmap.cape_verde, R.mipmap.czech_republic, R.mipmap.djibouti,
            R.mipmap.denmark, R.mipmap.dominican_republic, R.mipmap.algeria, R.mipmap.egypt,
            R.mipmap.eritrea, R.mipmap.ethiopia, R.mipmap.european_union, R.mipmap.fiji,
            R.mipmap.falkland_islands, R.mipmap.united_kingdom, R.mipmap.georgia, R.mipmap.guernsey,
            R.mipmap.ghana, R.mipmap.gibraltar, R.mipmap.gambia, R.mipmap.guinea,R.mipmap.guatemala,
            R.mipmap.guyana, R.mipmap.hong_kong, R.mipmap.honduras, R.mipmap.croatia,
            R.mipmap.haiti, R.mipmap.hungary, R.mipmap.indonesia, R.mipmap.israel,
            R.mipmap.isle_of_man, R.mipmap.india, R.mipmap.iraq, R.mipmap.iran, R.mipmap.iceland,
            R.mipmap.jersey, R.mipmap.jamaica, R.mipmap.jordan, R.mipmap.japan, R.mipmap.kenya,
            R.mipmap.kyrgyzstan, R.mipmap.cambodia, R.mipmap.comoros, R.mipmap.north_korea,
            R.mipmap.korea, R.mipmap.kuwait, R.mipmap.cayman_islands, R.mipmap.kazakhstan,
            R.mipmap.laos, R.mipmap.lebanon, R.mipmap.sri_lanka, R.mipmap.liberia, R.mipmap.lesotho,
            R.mipmap.libya, R.mipmap.morocco, R.mipmap.moldova, R.mipmap.madagascar,
            R.mipmap.macedonia, R.mipmap.myanmar, R.mipmap.mongolia, R.mipmap.macau,
            R.mipmap.mauritania, R.mipmap.mauritius, R.mipmap.maldives, R.mipmap.malawi,
            R.mipmap.mexico, R.mipmap.malaysia, R.mipmap.mozambique, R.mipmap.namibia,
            R.mipmap.nigeria, R.mipmap.nicaragua, R.mipmap.norway, R.mipmap.nepal,
            R.mipmap.new_zealand, R.mipmap.oman, R.mipmap.panama, R.mipmap.peru,
            R.mipmap.papua_new_guinea, R.mipmap.philippines, R.mipmap.pakistan, R.mipmap.poland,
            R.mipmap.paraguay, R.mipmap.qatar, R.mipmap.romania, R.mipmap.serbia, R.mipmap.russia,
            R.mipmap.rwanda, R.mipmap.saudi_arabia, R.mipmap.solomon_islands, R.mipmap.seychelles,
            R.mipmap.sudan, R.mipmap.sweden, R.mipmap.singapore, R.mipmap.saint_helena,
            R.mipmap.sierra_leone, R.mipmap.somalia, R.mipmap.suriname, R.mipmap.south_sudan,
            R.mipmap.sao_tome_and_principe, R.mipmap.el_salvador, R.mipmap.syria, R.mipmap.swaziland,
            R.mipmap.thailand, R.mipmap.tajikistan, R.mipmap.turkmenistan, R.mipmap.tunisia,
            R.mipmap.tonga, R.mipmap.turkey, R.mipmap.trinidad_and_tobago, R.mipmap.taiwan,
            R.mipmap.tanzania, R.mipmap.ukraine, R.mipmap.uganda, R.mipmap.united_states,
            R.mipmap.uruguay, R.mipmap.uzbekistan, R.mipmap.venezuela, R.mipmap.vietnam,
            R.mipmap.vanuatu, R.mipmap.samoa, R.mipmap.central_african_republic, R.mipmap.antigua_and_barbuda,
            R.mipmap.senegal, R.mipmap.new_caledonia, R.mipmap.yemen, R.mipmap.south_africa,
            R.mipmap.zambia, R.mipmap.zimbabwe

    };
    String[] currency = new String[]{
            "AED","AFN","ALL","AMD","ANG","AOA","ARS","AUD","AWG","AZN","BAM","BBD","BDT","BGN",
            "BHD","BIF","BMD","BND","BOB","BRL","BSD","BTC","BTN","BWP","BYN","BZD","CAD","CDF",
            "CDF","CHF","CLP","CLF","CNY","COP","CRC","CUC","CUP","CVE","CZK","DJF","DKK","DOP",
            "DZD","EGP","ERN","ETB","EUR","FJD","FKP","GBP","GEL","GGP","GHS","GIP","GMD","GNF",
            "GTQ","GYD","HKD","HNL","HRK","HTG","HUF","IDR","ILS","IMP","INR","IQD","IRR","ISK",
            "JEP","JMD","JOD","JPY","KES","KGS","KHR","KMF","KPW","KRW","KWD","KYD","KZT","LAK",
            "LBP","LKR","LRD","LSL","LYD","MAD","MDL","MGA","MKD","MMK","MNT","MOP","MRO","MUR",
            "MVR","MWk","MXN","MYR","MZN","NAD","NGN","NIO","NOK","NPR","NZD","OMR","PAB","PEN",
            "PGK","PHP","PKR","PLN","PYG","QAR","RON","RSD","RUB","RWF","SAR","SBD","SCR","SDG",
            "SEK","SDG","SHP","SLL","SOS","SRD","SSP","STD","SVC","SYP","SZL","THB","TJS","TMT",
            "TND","TOP","TRY","TTD","TWD","TZS","UAH","UGX","USD","UYU","UZS","VEF","VND","VUV",
            "WST","XAF","XCD","XOF","XPF","YER","ZAR","ZMW","ZWL"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);
        initView();
        initModel();
    }

    private void initView() {
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.rec_country);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void initModel() {
        Intent intent = getIntent();
        if (intent.getStringExtra("flag1") != null) {
            id_flag1 = intent.getIntExtra("id", 0);
            if (intent.getStringExtra("flag1").equals("flag1Country")) {
                setUpId(id_flag1);

            }
        } else if (intent.getStringExtra("flag2") != null) {
            id_flag1 = intent.getIntExtra("id", 0);

            if (intent.getStringExtra("flag2").equals("flag2Country")) {
                setUpId(id_flag1);

            }

        }
        mCountryList = new ArrayList<>();
        for (int i = 0; i < currencyNames.length; i++) {
            CurrencyModel country = new CurrencyModel();
            country.setmCountryName(currencyNames[i]);
            country.setFlag(flagId[i]);
            country.setmCurrencyiso(currency[i]);
            mCountryList.add(country);

            for(CurrencyModel model : mCountryList){
                setCurrencyModel(model);
            }

            mAdapter = new CountryListAdapter(getApplicationContext(), mCountryList, new CountryListAdapterListener() {
                @Override
                public void ItemClick(CurrencyModel model, int p) {
                    if (id==5){
                        Intent intent=new Intent();
                        intent.putExtra("map",model.getFlag());
                        intent.putExtra("name", model.getmCountryName());
                        intent.putExtra("iso", model.getmCurrencyiso());
                        setResult(2,intent);
                        finish();
                    }else if (id == 6){
                        Intent intent=new Intent();
                        intent.putExtra("map",model.getFlag());
                        intent.putExtra("name", model.getmCountryName());
                        intent.putExtra("iso", model.getmCurrencyiso());
                        setResult(3,intent);
                        finish();
                    }


                }


            });
            mRecyclerView.setAdapter(mAdapter);


        }
    }

    public void setCurrencyModel(CurrencyModel model) {
        this.mCurrencymodel = model;

    }

    public void setUpId(int id) {
        this.id = id;
    }
}

package com.skgezhil.allinone
import android.app.DownloadManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.text.DecimalFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

class CurrencyConverrter : AppCompatActivity() {

    private var brlConversion: Double = 0.3443
    private val cadConversion: Double = 1.0
    private var cnyConversion: Double = 0.2019
    private var eurConversion: Double = 1.5163
    private var hkdConversion: Double = 0.164082
    private var inrConversion: Double = 0.01894
    private var idrConversion: Double = 0.000091
    private var jpyConversion: Double = 0.01163
    private var myrConversion: Double = 0.3243
    private var mxnConversion: Double = 0.06468
    private var nzdConversion: Double = 0.8902
    private var nokConversion: Double = 0.1587
    private var penConversion: Double = 0.3920
    private var rubConversion: Double = 0.02066
    private var sarConversion: Double = 0.3435
    private var sgdConversion: Double = 0.9587
    private var zarConversion: Double = 0.10106
    private var krwConversion: Double = 0.001190
    private var sekConversion: Double = 0.1472
    private var chfConversion: Double = 1.2904
    private var twdConversion: Double = 0.04300
    private var thbConversion: Double = 0.04000
    private var tryConversion: Double = 0.2869
    private var gbpConversion: Double = 1.735
    private var usdConversion: Double = 1.2880
    private var vndConversion: Double = 0.000057
    var startConversion: Double = 1.0
    var localConversion: Double = 1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Call the API to retrieve the current startConversion rates
        val sharedPref: SharedPreferences = this@CurrencyConverrter.getPreferences(Context.MODE_PRIVATE) ?: return
        val lastAPICall = sharedPref.getLong(getString(R.string.saved_api_call_key), Long.MIN_VALUE)
        val lastTimestamp = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Instant.ofEpochMilli(lastAPICall).atZone(ZoneId.systemDefault()).toLocalDate()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val daysBetween = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ChronoUnit.DAYS.between(lastTimestamp, LocalDate.now())
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        if (daysBetween > 1) {
            getConversionValuesFromAPI()
        } else {
            loadConversionFromSharedPreferences()
        }

        val currencySpinner = findViewById<Spinner>(R.id.currency_spinner)
        //currencySpinner.setSelection(0, false)
        currencySpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                val selected = parent.getItemAtPosition(pos).toString()
                when (selected) {
                    "Brazilian real" -> startConversion = brlConversion
                    "Canadian dollar" -> startConversion = cadConversion
                    "Chinese renminbi" -> startConversion = cnyConversion
                    "European euro" -> startConversion = eurConversion
                    "Hong Kong dollar" -> startConversion = hkdConversion
                    "Indian rupee" -> startConversion = inrConversion
                    "Indonesian rupiah" -> startConversion = idrConversion
                    "Japanese yen" -> startConversion = jpyConversion
                    "Malaysian ringgit" -> startConversion = myrConversion
                    "Mexican peso" -> startConversion = mxnConversion
                    "New Zealand dollar" -> startConversion = nzdConversion
                    "Norwegian kron" -> startConversion = nokConversion
                    "Peruvian new sol" -> startConversion = penConversion
                    "Russian ruble" -> startConversion = rubConversion
                    "Saudi riyal" -> startConversion = sarConversion
                    "Singapore dollar" -> startConversion = sgdConversion
                    "South African rand" -> startConversion = sarConversion
                    "South Korean won" -> startConversion = krwConversion
                    "Swedish krona" -> startConversion = sekConversion
                    "Swiss franc" -> startConversion = chfConversion
                    "Taiwanese dollar" -> startConversion = twdConversion
                    "Thai baht" -> startConversion = thbConversion
                    "Turkish lira" -> startConversion = tryConversion
                    "UK pound sterling" -> startConversion = gbpConversion
                    "US dollar" -> startConversion = usdConversion
                    "Vietnamese dong" -> startConversion = vndConversion
                }
            }
        }

        val localCurrencySpinner = findViewById<Spinner>(R.id.currency_spinner2)
        //localCurrencySpinner.setSelection(0, false)
        localCurrencySpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                val selected = parent.getItemAtPosition(pos).toString()
                when (selected) {
                    "Brazilian real" -> localConversion = brlConversion
                    "Canadian dollar" -> localConversion = cadConversion
                    "Chinese renminbi" -> localConversion = cnyConversion
                    "European euro" -> localConversion = eurConversion
                    "Hong Kong dollar" -> localConversion = hkdConversion
                    "Indian rupee" -> localConversion = inrConversion
                    "Indonesian rupiah" -> localConversion = idrConversion
                    "Japanese yen" -> localConversion = jpyConversion
                    "Malaysian ringgit" -> localConversion = myrConversion
                    "Mexican peso" -> localConversion = mxnConversion
                    "New Zealand dollar" -> localConversion = nzdConversion
                    "Norwegian kron" -> localConversion = nokConversion
                    "Peruvian new sol" -> localConversion = penConversion
                    "Russian ruble" -> localConversion = rubConversion
                    "Saudi riyal" -> localConversion = sarConversion
                    "Singapore dollar" -> localConversion = sgdConversion
                    "South African rand" -> localConversion = sarConversion
                    "South Korean won" -> localConversion = krwConversion
                    "Swedish krona" -> localConversion = sekConversion
                    "Swiss franc" -> localConversion = chfConversion
                    "Taiwanese dollar" -> localConversion = twdConversion
                    "Thai baht" -> localConversion = thbConversion
                    "Turkish lira" -> localConversion = tryConversion
                    "UK pound sterling" -> localConversion = gbpConversion
                    "US dollar" -> localConversion = usdConversion
                    "Vietnamese dong" -> localConversion = vndConversion
                }
            }
        }


    }


    private fun getConversionValuesFromAPI() {
        val url = "https://www.bankofcanada.ca/valet/observations/group/FX_RATES_DAILY/json?recent=1"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,Response.Listener { response ->
            try {



                val rates = response.getJSONArray("observations").getJSONObject(0)
                val sharedPref = this@CurrencyConverrter.getPreferences(Context.MODE_PRIVATE)


                brlConversion = getExchangeRate(rates, "FXBRLCAD")
                cnyConversion = getExchangeRate(rates, "FXCNYCAD")
                eurConversion = getExchangeRate(rates, "FXEURCAD")
                hkdConversion = getExchangeRate(rates, "FXHKDCAD")
                inrConversion = getExchangeRate(rates, "FXINRCAD")
                idrConversion = getExchangeRate(rates, "FXIDRCAD")
                jpyConversion = getExchangeRate(rates, "FXJPYCAD")
                myrConversion = getExchangeRate(rates, "FXMYRCAD")
                mxnConversion = getExchangeRate(rates, "FXMXNCAD")
                nzdConversion = getExchangeRate(rates, "FXNZDCAD")
                nokConversion = getExchangeRate(rates, "FXNOKCAD")
                penConversion = getExchangeRate(rates, "FXPENCAD")
                rubConversion = getExchangeRate(rates, "FXRUBCAD")
                sarConversion = getExchangeRate(rates, "FXSARCAD")
                sgdConversion = getExchangeRate(rates, "FXSGDCAD")
                zarConversion = getExchangeRate(rates, "FXZARCAD")
                krwConversion = getExchangeRate(rates, "FXKRWCAD")
                sekConversion = getExchangeRate(rates, "FXSEKCAD")
                chfConversion = getExchangeRate(rates, "FXCHFCAD")
                twdConversion = getExchangeRate(rates, "FXTWDCAD")
                thbConversion = getExchangeRate(rates, "FXTHBCAD")
                tryConversion = getExchangeRate(rates, "FXTRYCAD")
                gbpConversion = getExchangeRate(rates, "FXGBPCAD")
                usdConversion = getExchangeRate(rates, "FXUSDCAD")
                vndConversion = getExchangeRate(rates, "FXVNDCAD")

                // Save today as the last time the API was called, so that we
                // don't call the API more than once a day
                with(sharedPref.edit()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        putLong(getString(R.string.saved_api_call_key), LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                    }

                    putDouble(getString(R.string.brl_conversion), brlConversion)
                    putDouble(getString(R.string.cny_conversion), cnyConversion)
                    putDouble(getString(R.string.eur_conversion), eurConversion)
                    putDouble(getString(R.string.hkd_conversion), hkdConversion)
                    putDouble(getString(R.string.inr_conversion), inrConversion)
                    putDouble(getString(R.string.idr_conversion), idrConversion)
                    putDouble(getString(R.string.jpy_conversion), jpyConversion)
                    putDouble(getString(R.string.myr_conversion), myrConversion)
                    putDouble(getString(R.string.mxn_conversion), mxnConversion)
                    putDouble(getString(R.string.nzd_conversion), nzdConversion)
                    putDouble(getString(R.string.nok_conversion), nokConversion)
                    putDouble(getString(R.string.pen_conversion), penConversion)
                    putDouble(getString(R.string.rub_conversion), rubConversion)
                    putDouble(getString(R.string.sar_conversion), sarConversion)
                    putDouble(getString(R.string.sgd_conversion), sgdConversion)
                    putDouble(getString(R.string.zar_conversion), zarConversion)
                    putDouble(getString(R.string.krw_conversion), krwConversion)
                    putDouble(getString(R.string.sek_conversion), sekConversion)
                    putDouble(getString(R.string.chf_conversion), chfConversion)
                    putDouble(getString(R.string.twd_conversion), twdConversion)
                    putDouble(getString(R.string.thb_conversion), thbConversion)
                    putDouble(getString(R.string.try_conversion), tryConversion)
                    putDouble(getString(R.string.gbp_conversion), gbpConversion)
                    putDouble(getString(R.string.usd_conversion), usdConversion)
                    putDouble(getString(R.string.vnd_conversion), vndConversion)

                    apply()
                }

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, Response.ErrorListener {
            // Purposely do nothing if we can't reach the API
        })
        Volley.newRequestQueue(this).add(jsonObjectRequest)
    }

    companion object {
        val EXTRA_CONVERSION = "com.lucasmelin.currencyconverter.CONVERSION"
    }

    private fun getExchangeRate(jsonObj: JSONObject, key: String): Double {
        return jsonObj.getJSONObject(key).getDouble("v")
    }


    private fun loadConversionFromSharedPreferences() {
        val sharedPref = this@CurrencyConverrter.getPreferences(Context.MODE_PRIVATE)
        val conversionDefault = 1.0


        brlConversion = sharedPref.getDouble(getString(R.string.brl_conversion), conversionDefault)
        cnyConversion = sharedPref.getDouble(getString(R.string.cny_conversion), conversionDefault)
        eurConversion = sharedPref.getDouble(getString(R.string.eur_conversion), conversionDefault)
        hkdConversion = sharedPref.getDouble(getString(R.string.hkd_conversion), conversionDefault)
        inrConversion = sharedPref.getDouble(getString(R.string.inr_conversion), conversionDefault)
        idrConversion = sharedPref.getDouble(getString(R.string.idr_conversion), conversionDefault)
        jpyConversion = sharedPref.getDouble(getString(R.string.jpy_conversion), conversionDefault)
        myrConversion = sharedPref.getDouble(getString(R.string.myr_conversion), conversionDefault)
        mxnConversion = sharedPref.getDouble(getString(R.string.mxn_conversion), conversionDefault)
        nzdConversion = sharedPref.getDouble(getString(R.string.nzd_conversion), conversionDefault)
        nokConversion = sharedPref.getDouble(getString(R.string.nok_conversion), conversionDefault)
        penConversion = sharedPref.getDouble(getString(R.string.pen_conversion), conversionDefault)
        rubConversion = sharedPref.getDouble(getString(R.string.rub_conversion), conversionDefault)
        sarConversion = sharedPref.getDouble(getString(R.string.sar_conversion), conversionDefault)
        sgdConversion = sharedPref.getDouble(getString(R.string.sgd_conversion), conversionDefault)
        zarConversion = sharedPref.getDouble(getString(R.string.zar_conversion), conversionDefault)
        krwConversion = sharedPref.getDouble(getString(R.string.krw_conversion), conversionDefault)
        sekConversion = sharedPref.getDouble(getString(R.string.sek_conversion), conversionDefault)
        chfConversion = sharedPref.getDouble(getString(R.string.chf_conversion), conversionDefault)
        twdConversion = sharedPref.getDouble(getString(R.string.twd_conversion), conversionDefault)
        thbConversion = sharedPref.getDouble(getString(R.string.thb_conversion), conversionDefault)
        tryConversion = sharedPref.getDouble(getString(R.string.try_conversion), conversionDefault)
        gbpConversion = sharedPref.getDouble(getString(R.string.gbp_conversion), conversionDefault)
        usdConversion = sharedPref.getDouble(getString(R.string.usd_conversion), conversionDefault)
        vndConversion = sharedPref.getDouble(getString(R.string.vnd_conversion), conversionDefault)
    }

    private fun SharedPreferences.Editor.putDouble(key: String, double: Double) =
        putLong(key, java.lang.Double.doubleToRawLongBits(double))

    private fun SharedPreferences.getDouble(key: String, default: Double) =
        java.lang.Double.longBitsToDouble(getLong(key, java.lang.Double.doubleToRawLongBits(default)))


    fun convertCurrency(view: View) {
        val startDollars: Double
        val localAmount: Double
        // Regex to validate the input, even though the EditText is set
        // to entering only numbers
        val validDouble = "^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$"
        // Format to use when outputting converted currency
        val currencyFormat = DecimalFormat("00.00")

        val startAmountText = findViewById<EditText>(R.id.cdn_dollars)
        val localCurrencyText = findViewById<EditText>(R.id.local_currency_amount)

        // Get the amounts that have been entered in the EditText boxes
        val cdnTextValue = startAmountText.text.toString()
        val localTextValue = localCurrencyText.text.toString()

        // If a valid number was entered in the Start EditText field, convert to
        // LocalCurrency
        if ("" != cdnTextValue && cdnTextValue.matches(validDouble.toRegex())) {
            // Convert from start to Local Currency
            startDollars = java.lang.Double.parseDouble(cdnTextValue)
            localAmount = startDollars * (startConversion.div(localConversion))
            // Format the double to a string with two decimal places before outputting
            localCurrencyText.setText(currencyFormat.format(localAmount))
        } else if ("" != localTextValue && localTextValue.matches(validDouble.toRegex())) {
            // Convert from Local Currency to start
            localAmount = java.lang.Double.parseDouble(localTextValue)
            startDollars = localAmount * (localConversion.div(startConversion))
            // Format the double to a string with two decimal places before outputting
            startAmountText.setText(currencyFormat.format(startDollars))
        }
    }

    /**
     * Called when the user taps the Clear Amounts button
     */
    fun clearCurrencyFields(view: View) {
        // Get both EditText fields
        val cdnAmountText = findViewById<EditText>(R.id.cdn_dollars)
        val localCurrencyText = findViewById<EditText>(R.id.local_currency_amount)
        // Clear the EditText fields
        cdnAmountText.text.clear()
        localCurrencyText.text.clear()
    }

    // Nullable division operator
    operator fun Double?.div(other: Double?) = if (this != null && other != null) this / other else null

    // Nullable multiplication operator
    operator fun Double?.times(other: Double?) = if (this != null && other != null) this * other else null


}

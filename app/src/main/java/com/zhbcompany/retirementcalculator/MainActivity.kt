package com.zhbcompany.retirementcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCenter.start(
            application, "b4f1ca28-165b-46a6-b1ad-10491c4a9c7b",
            Analytics::class.java, Crashes::class.java
        )

        val calculateButton: Button = findViewById(R.id.calculateButton)
        val interestEditText: EditText = findViewById(R.id.interestEditText)
        val ageEditText: EditText = findViewById(R.id.ageEditText)
        val retirementEditText: EditText = findViewById(R.id.retirementEditText)
        val monthlySavingsEditText: EditText = findViewById(R.id.monthlySavingsEditText)
        val currentEditText: EditText = findViewById(R.id.currentEditText)
        val resultTextView: TextView = findViewById(R.id.resultTextView)

        calculateButton.setOnClickListener {
            try {
                val interestRate = interestEditText.text.toString().toFloat()
                val currentAge = ageEditText.text.toString().toInt()
                val retirementAge = retirementEditText.text.toString().toInt()
                val monthly = monthlySavingsEditText.text.toString().toFloat()
                val current = currentEditText.text.toString().toFloat()

                val properties: HashMap<String, String> = HashMap()
                properties["interest_rate"] = interestRate.toString()
                properties["current_age"] = currentAge.toString()
                properties["retirement_age"] = retirementAge.toString()
                properties["monthly_savings"] = monthly.toString()
                properties["current_savings"] = current.toString()

                if (interestRate <= 0) {
                    Analytics.trackEvent("wrong_interest_rate", properties)
                }
                if (retirementAge <= currentAge) {
                    Analytics.trackEvent("wrong_age", properties)
                }

                resultTextView.text = "At the current rate of $interestRate%, saving \$$monthly a month you will have \$X by $retirementAge."
            } catch (ex: Exception) {
                Analytics.trackEvent(ex.message)
            }
        }
    }
}

package com.example.mycity.Database

import com.example.mycity.R

object DataSource {

    val restaurants = listOf(
        Place("Uluk - Ata", "Best cafe in the city.", R.drawable.ulukatarest),
        Place("Mimino", "Delicious Italian pizza.", imageRes = R.drawable.mimino),
        Place("Dolce Vita", "Famous for big burgers.", imageRes = R.drawable.dolce_vita),
        Place("Dolce Vita", "Famous for big burgers.", imageRes = R.drawable.dolce_vita)
    )

    val parks = listOf(
        Place("Toktogul park", "Perfect place for walking.", imageRes = R.drawable.toktogul),
        Place("Navoi Park", "Beautiful flowers and lake.", imageRes = R.drawable.navoi),
        Place("Sunset Park", "Amazing sunset view.", imageRes = R.drawable.chernobylmemorial)
    )

    val museums = listOf(
        Place("Sulaiman Too", "Learn about local history.", imageRes = R.drawable.sulaiman),
        Place("Historical museum", "Modern art exhibition.", imageRes = R.drawable.historical),
        Place("Kyrgyz National Museum", "Interactive science experiments.", imageRes = R.drawable.national)
    )
}

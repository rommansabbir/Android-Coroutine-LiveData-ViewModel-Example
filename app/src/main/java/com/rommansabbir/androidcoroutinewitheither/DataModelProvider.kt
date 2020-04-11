package com.rommansabbir.androidcoroutinewitheither

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

object DataModelProvider {

    /**
     * Fake network data fetching process
     * Make a [delay] for 5 seconds
     * Note this, this is a [suspend] function, this must be calling from a [CoroutineScope]
     *
     * @return [MutableList<DataModel>], return a list of [DataModel]
     */
    suspend fun getModels(): MutableList<DataModel> {
        delay(5000)
        val list = mutableListOf<DataModel>()
        list.add(DataModel("Dell E5250", 780.00, "E5250"))
        list.add(
            DataModel(
                "Acer Aspire A315-21 46ZB AMD-A4-9120E 15.6 HD Laptop",
                780.00,
                "Acer Aspire A315-21 46ZB"
            )
        )
        list.add(
            DataModel(
                "Acer Aspire A315-31 C421 Intel Celeron Dual Core 15.6",
                780.00,
                "Acer Aspire A315-31"
            )
        )
        list.add(
            DataModel(
                "Asus AsusPro P1440 8th Gen Core i3 8145U 14.0",
                780.00,
                "Asus AsusPro P1440"
            )
        )
        list.add(
            DataModel(
                "AVITA LIBER NS13A2 Core i5 8th Gen 13.3 Full HD Champagne Gold Color Laptop with Windows 10",
                780.00,
                "AVITA LIBER NS13A2"
            )
        )
        list.add(
            DataModel(
                "Avita Admiror Core i7 8th Gen 14 Full HD Laptop with Windows 10",
                780.00,
                "E5250"
            )
        )
        list.add(
            DataModel(
                "Asus VivoBook X402YA AMD Dual Core 14 HD Laptop With Genuine Windows 10",
                780.00,
                "Asus VivoBook X402YA "
            )
        )
        list.add(
            DataModel(
                "AVITA LIBER NS13A2 Core i5 8th Gen 13.3 Full HD Cherry Blossom Pink Color Laptop with Windows 10",
                780.00,
                "AVITA LIBER NS13A2"
            )
        )
        list.add(
            DataModel(
                "AVITA LIBER NS13A2 Core i5 8th Gen 13.3 Full HD Flowers on Yellow Color Laptop with Windows 10",
                780.00,
                "AVITA LIBER NS13A2"
            )
        )
        list.add(
            DataModel(
                "AVITA LIBER NS13A2 Core i5 8th Gen 13.3 Full HD Matt Black Laptop with Windows 10",
                780.00,
                "AVITA LIBER NS13A2"
            )
        )
        list.add(
            DataModel(
                "Dell Inspiron 15 3580 Intel CDC 4205U 15.6 inch HD Laptop with Genuine Windows 10",
                780.00,
                "Dell Inspiron 15 3580"
            )
        )
        list.add(
            DataModel(
                "Dell Inspiron 15 3580 Intel CDC 4205U 15.6 inch HD Laptop with Genuine Windows 10",
                780.00,
                "Dell Inspiron 15 3580"
            )
        )
        list.add(
            DataModel(
                "Gigabyte AERO 15 WA-7US5130SP Core i7 9th Gen RTX 2060 15.6 inch OLED UHD AMOLED Gaming Laptop",
                780.00,
                "AERO 15 WA-7US5130SP"
            )
        )
        list.add(
            DataModel(
                "Gigabyte AERO 15-SA Core i7 9th Gen GTX 1660 Ti 15.6 inch OLED UHD AMOLED Gaming Laptop",
                780.00,
                "Gigabyte AERO 15-SA"
            )
        )
        list.add(
            DataModel(
                "Dell Inspiron 14-3480 Intel CDC 4205U 14.0 inch HD Laptop with Genuine Windows 10",
                780.00,
                "Dell Inspiron 14-3480"
            )
        )
        list.add(
            DataModel(
                "AVITA PURA NS14A6 Core i3 8th Gen 14.0 Inch Full HD Metallic Black Laptop with Windows 10",
                780.00,
                "AVITA PURA NS14A6"
            )
        )
        return list
    }


}
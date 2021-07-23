package com.temporary.myapplication.pojoModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class RocketDetailsResponseModel {

    class CompositeFairing {
        @SerializedName("height")
        @Expose
        var compositeFairingHeight: CompositeFairingHeight? = null

        @SerializedName("diameter")
        @Expose
        var compositeFairingDiameter: CompositeFairingDiameter? = null

    }
    class Diameter {
        @SerializedName("meters")
        @Expose
        var meters: Int = 0

        @SerializedName("feet")
        @Expose
        var feet: Int = 0

    }

    class CompositeFairingDiameter {
        @SerializedName("meters")
        @Expose
        var meters: Any? = null

        @SerializedName("feet")
        @Expose
        var feet: Any? = null

    }

    class Engines {
        @SerializedName("isp")
        @Expose
        var isp: Isp? = null

        @SerializedName("thrust_sea_level")
        @Expose
        var thrustSeaLevel: EnginesThrustSeaLevel? = null

        @SerializedName("thrust_vacuum")
        @Expose
        var enginesThrustVacuum: EnginesThrustVacuum? = null

        @SerializedName("number")
        @Expose
        var number: Int = 0

        @SerializedName("type")
        @Expose
        var type: String? = null

        @SerializedName("version")
        @Expose
        var version: String? = null

        @SerializedName("layout")
        @Expose
        var layout: Any? = null

        @SerializedName("engine_loss_max")
        @Expose
        var engineLossMax: Any? = null

        @SerializedName("propellant_1")
        @Expose
        var propellant1: String? = null

        @SerializedName("propellant_2")
        @Expose
        var propellant2: String? = null

        @SerializedName("thrust_to_weight")
        @Expose
        var thrustToWeight: Int = 0

    }


        @SerializedName("height")
        @Expose
        var height: Height? = null

        @SerializedName("diameter")
        @Expose
        var diameter: Diameter? = null

        @SerializedName("mass")
        @Expose
        var mass: Mass? = null

        @SerializedName("first_stage")
        @Expose
        var firstStage: FirstStage? = null

        @SerializedName("second_stage")
        @Expose
        var secondStage: SecondStage? = null

        @SerializedName("engines")
        @Expose
        var engines: Engines? = null

        @SerializedName("landing_legs")
        @Expose
        var landingLegs: LandingLegs? = null

        @SerializedName("payload_weights")
        @Expose
        var payloadWeights: List<PayloadWeight>? = null

        @SerializedName("flickr_images")
        @Expose
        var flickrImages: List<String>? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("type")
        @Expose
        var type: String? = null

        @SerializedName("active")
        @Expose
        var active: Boolean = false

        @SerializedName("stages")
        @Expose
        var stages: Int = 0

        @SerializedName("boosters")
        @Expose
        var boosters: Int = 0

        @SerializedName("cost_per_launch")
        @Expose
        var costPerLaunch: Int = 0

        @SerializedName("success_rate_pct")
        @Expose
        var successRatePct: Int = 0

        @SerializedName("first_flight")
        @Expose
        var firstFlight: String? = null

        @SerializedName("country")
        @Expose
        var country: String? = null

        @SerializedName("company")
        @Expose
        var company: String? = null

        @SerializedName("wikipedia")
        @Expose
        var wikipedia: String? = null

        @SerializedName("description")
        @Expose
        var description: String? = null

        @SerializedName("id")
        @Expose
        var id: String? = null


    class FirstStage {
        @SerializedName("thrust_sea_level")
        @Expose
        var firstStageThrustSeaLevel: FirstStageThrustSeaLevel? = null

        @SerializedName("thrust_vacuum")
        @Expose
        var firstStageThrustVacuum: FirstStageThrustVacuum? = null

        @SerializedName("reusable")
        @Expose
        var reusable: Boolean = false

        @SerializedName("engines")
        @Expose
        var engines: Int = 0

        @SerializedName("fuel_amount_tons")
        @Expose
        var fuelAmountTons: Int = 0

        @SerializedName("burn_time_sec")
        @Expose
        var burnTimeSec: Any? = null

    }
    class Height {
        @SerializedName("meters")
        @Expose
        var meters: Int = 0

        @SerializedName("feet")
        @Expose
        var feet: Int = 0

    }

    class CompositeFairingHeight {
        @SerializedName("meters")
        @Expose
        var meters: Any? = null

        @SerializedName("feet")
        @Expose
        var feet: Any? = null

    }
    class Isp {
        @SerializedName("sea_level")
        @Expose
        var seaLevel: Int = 0

        @SerializedName("vacuum")
        @Expose
        var vacuum: Int = 0

    }
    class LandingLegs {
        @SerializedName("number")
        @Expose
        var number: Int = 0

        @SerializedName("material")
        @Expose
        var material: String? = null

    }

    class Mass {
        @SerializedName("kg")
        @Expose
        var kg: Int = 0

        @SerializedName("lb")
        @Expose
        var lb: Int = 0

    }

    class PayloadWeight {
        @SerializedName("id")
        @Expose
        var id: String? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("kg")
        @Expose
        var kg: Int = 0

        @SerializedName("lb")
        @Expose
        var lb: Int = 0

    }
    class Payloads {
        @SerializedName("composite_fairing")
        @Expose
        var compositeFairing: CompositeFairing? = null

        @SerializedName("option_1")
        @Expose
        var option1: String? = null

    }

    class SecondStage {
        @SerializedName("thrust")
        @Expose
        var secondStageThrust: SecondStageThrust? = null

        @SerializedName("payloads")
        @Expose
        var payloads: Payloads? = null

        @SerializedName("reusable")
        @Expose
        var reusable: Boolean = false

        @SerializedName("engines")
        @Expose
        var engines: Int = 0

        @SerializedName("fuel_amount_tons")
        @Expose
        var fuelAmountTons: Int = 0

        @SerializedName("burn_time_sec")
        @Expose
        var burnTimeSec: Any? = null

    }

    class SecondStageThrust {
        @SerializedName("kN")
        @Expose
        private var kN: Int = 0

        @SerializedName("lbf")
        @Expose
        var lbf: Int = 0

        fun getkN(): Int {
            return kN
        }

        fun setkN(kN: Int) {
            this.kN = kN
        }

    }
    class FirstStageThrustSeaLevel {
        @SerializedName("kN")
        @Expose
        private var kN: Int = 0

        @SerializedName("lbf")
        @Expose
        var lbf: Int = 0

        fun getkN(): Int {
            return kN
        }

        fun setkN(kN: Int) {
            this.kN = kN
        }

    }

    class EnginesThrustSeaLevel {
        @SerializedName("kN")
        @Expose
        private var kN: Int = 0

        @SerializedName("lbf")
        @Expose
        var lbf: Int = 0

        fun getkN(): Int {
            return kN
        }

        fun setkN(kN: Int) {
            this.kN = kN
        }

    }

    class FirstStageThrustVacuum {
        @SerializedName("kN")
        @Expose
        private var kN: Int = 0

        @SerializedName("lbf")
        @Expose
        var lbf: Int = 0

        fun getkN(): Int {
            return kN
        }

        fun setkN(kN: Int) {
            this.kN = kN
        }

    }

    class EnginesThrustVacuum {
        @SerializedName("kN")
        @Expose
        private var kN: Int = 0

        @SerializedName("lbf")
        @Expose
        var lbf: Int = 0

        fun getkN(): Int {
            return kN
        }

        fun setkN(kN: Int) {
            this.kN = kN
        }
    }
}
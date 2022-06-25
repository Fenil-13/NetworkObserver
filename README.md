# Network Observer - Android

NetworkObserver for Android, continuous network check in android

## Setup

The simplest way to use NetworkObserver is to add the library as aar dependency to your build.

**Gradle**

    repositories {
        maven { url 'https://jitpack.io' }
    }

    dependencies {
        implementation 'com.github.Fenil-13:NetworkObserver:1.0.2'
    }

## Usage

getInstance and give context,implement 2 methods and give lifecycle which you want to observe

    NetworkObserver.getInstance(context,object : NetworkListener {
            override fun onNetworkAvailable() {
                Toast.makeText(applicationContext, "Network is Available", Toast.LENGTH_SHORT).show()
            }

            override fun onNetworkLoss() {
                Toast.makeText(applicationContext, "Network is Loss", Toast.LENGTH_SHORT).show()
            }
        }, this)

**Perameter Understanding**:

- context : We need to pass context of where you observe the NetworkObserver

---

- onNetworkAvailable() : Here you need to implement logic which fire on when internet is available.

---

- onNetworkLoss() : Here you need to implement logic which fire on when internet is not available.

---

- lifecycle reference : Here you need to give lifecycle which you want to observe

# Donation

If this project help you reduce time to develop, you can give me a cup of coffee :)

- [click Here](https://rzp.io/l/networkobserverandroid)

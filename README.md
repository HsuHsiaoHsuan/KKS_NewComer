# data source
https://www.travel.taipei/open-api/swagger/docs/V1

# use tech
1. MVVM with [Hilt](https://developer.android.com/training/dependency-injection/hilt-android#hilt-and-dagger) 
2. Network: Retrofit + OkHttp
3. Coil to load Image
4. Moshi to parse JSON response
5. Timber to show debug message
6. use Paging3 to load paged RESTful data
7. use Navigation component with Safe Args to pass value between Fragments.
8. use kotlin-parcelize to support data Parcelable
9. use [CircleIndicator](https://github.com/ongakuer/CircleIndicator)

# migrate to Jetpack Compose
1. [doc](https://developer.android.com/jetpack/compose/migrate/strategy)
2. [codelab](https://developer.android.com/codelabs/jetpack-compose-migration#0)
3. choose correct compose compiler version [link](https://developer.android.com/jetpack/androidx/releases/compose-kotlin)
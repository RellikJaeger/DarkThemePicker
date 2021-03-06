package prieto.fernando.darkmodepicker.widget

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.BuildCompat
import dagger.Reusable
import io.reactivex.Single
import javax.inject.Inject

@Reusable
class ThemeApplier @Inject constructor() {
    private var selectedTheme = ThemeMode.DEFAULT

    fun initTheme(): Single<Unit> {
        when (selectedTheme) {
            ThemeMode.LIGHT -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            ThemeMode.DARK -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            else -> {
                if (BuildCompat.isAtLeastQ()) {
                    selectedTheme = ThemeMode.DARK
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                } else {
                    selectedTheme = ThemeMode.LIGHT
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }
            }
        }
        return Single.just(Unit)
    }

    fun applyTheme(themeMode: ThemeMode): Single<Unit> {
        when (themeMode) {
            ThemeMode.LIGHT -> {
                selectedTheme = ThemeMode.LIGHT
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            ThemeMode.DARK -> {
                selectedTheme = ThemeMode.DARK
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            else -> {
                selectedTheme = ThemeMode.DEFAULT
                if (BuildCompat.isAtLeastQ()) {
                    selectedTheme = ThemeMode.DARK
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                } else {
                    selectedTheme = ThemeMode.LIGHT
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }
            }
        }
        return Single.just(Unit)
    }

    fun getCurrentMode() = Single.just(selectedTheme)
}

enum class ThemeMode {
    LIGHT,
    DARK,
    DEFAULT
}

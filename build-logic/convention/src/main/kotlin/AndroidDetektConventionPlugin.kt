import com.example.android_clean_architecture.configureDetekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidDetektConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.run {
            pluginManager.apply {
//                apply(libs.findLibrary("detekt-gradlePlugin").get().get().group.toString())
            }
            extensions.configure<DetektExtension> {
                configureDetekt(this)
            }
        }
    }
}

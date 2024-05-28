import com.example.android_clean_architecture.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            dependencies {
                add("androidTestImplementation", libs.findLibrary("androidx.junit").get())
                add("testImplementation", libs.findLibrary("androidx.core.testing").get())
                add("testImplementation", libs.findLibrary("kotlinx.coroutines.test").get())
                add("testImplementation", libs.findLibrary("junit").get())
                add("testImplementation", libs.findLibrary("mockk").get())
                add("testImplementation", libs.findLibrary("mockk.agent.jvm").get())
                add("testImplementation", libs.findLibrary("mockito.core").get())
                add("testImplementation", libs.findLibrary("mockito.inline").get())
                add("androidTestImplementation", libs.findLibrary("espresso.core").get())
            }
        }
    }

}

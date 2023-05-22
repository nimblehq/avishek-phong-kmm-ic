package co.nimblehq.avishek.phong.kmmic.domain.usecase

import co.nimblehq.avishek.phong.kmmic.BuildKonfig
import io.kotest.matchers.shouldBe
import org.junit.Test

class GetAppVersionUseCaseImplTest {

    private val getAppVersionUseCase = GetAppVersionUseCaseImpl()

    @Test
    fun `When invoked, the use case reuturns the app version accordingly`(){
        getAppVersionUseCase() shouldBe "v${BuildKonfig.VERSION_NAME} (${BuildKonfig.VERSION_CODE})"
    }
}

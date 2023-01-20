package com.gdsc.boolpyeon.like_store

import com.gdsc.boolpyeon.BoolPyeonApplication
import com.gdsc.boolpyeon.store.Store
import com.gdsc.boolpyeon.store.StoreRepository
import com.gdsc.boolpyeon.user.domain.User
import com.gdsc.boolpyeon.user.repository.UserRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(classes = [BoolPyeonApplication::class])
@Transactional
class LikeStoreServiceTest(
    private val userRepository: UserRepository,
    private val storeRepository: StoreRepository,
    private val likeStoreService: LikeStoreService,
    private val likeStoreRepository: LikeStoreRepository,
) : StringSpec({

    "좋아하는 편의점이 없으면 빈 리스트를 반환한다." {
        val user = userRepository.save(User.fixture())
        val likeStores = likeStoreService.getLikeStores(user)

        likeStores.size shouldBe 0
    }

    "좋아하는 편의점을 성공적으로 추가한다." {
        val user = userRepository.save(User.fixture())
        val store = storeRepository.save(Store())

        likeStoreService.addLikeStore(user, store)

        val likeStores = likeStoreRepository.findAllByUser(user)
        likeStores.size shouldBe 1
    }

    // TODO("Store Entity 변경이 필요해보임")
    /**
     * 아래 테스트는 의미가 현재는 의미가 없는 테스트입니다.
     * Store 의 모든 속성이 null 이고 값을 설정할 수 없습니다.
     * Store Entity 변경 후 다시 테스트할 필요가 있습니다.
     */
    "이미 좋아하는 편의점으로 등록된 편의점은 예외를 발생한다." {
        val user = userRepository.save(User.fixture())
        val store = storeRepository.save(Store())

        likeStoreService.addLikeStore(user, store)
        shouldThrow<IllegalArgumentException> {
            likeStoreService.addLikeStore(user, store)
        }.message shouldBe "이미 좋아하는 편의점입니다."
    }

})

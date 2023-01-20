package com.gdsc.boolpyeon.user.service

import com.gdsc.boolpyeon.BoolPyeonApplication
import com.gdsc.boolpyeon.item.entity.DiscountItem
import com.gdsc.boolpyeon.item.repository.DiscountItemRepository
import com.gdsc.boolpyeon.like_item.LikeItem
import com.gdsc.boolpyeon.like_item.LikeItemRepository
import com.gdsc.boolpyeon.like_store.LikeStore
import com.gdsc.boolpyeon.like_store.LikeStoreRepository
import com.gdsc.boolpyeon.store.Store
import com.gdsc.boolpyeon.store.StoreRepository
import com.gdsc.boolpyeon.user.domain.User
import com.gdsc.boolpyeon.user.domain.dto.request.UserCreateRequest
import com.gdsc.boolpyeon.user.domain.dto.request.UserModifyRequest
import com.gdsc.boolpyeon.user.repository.UserRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional

@SpringBootTest(classes = [BoolPyeonApplication::class])
@Transactional
class UserServiceImplTest(
    private val userService: UserService,
    private val userRepository: UserRepository,
    private val storeRepository: StoreRepository,
    private val likeStoreRepository: LikeStoreRepository,
    private val discountItemRepository: DiscountItemRepository,
    private val likeItemRepository: LikeItemRepository,
) : StringSpec({

    beforeEach {
        userRepository.deleteAll()
//        storeRepository.deleteAll()
//        discountItemRepository.deleteAll()
    }

    // Create
    "유저를 저장한다." {
        val request = UserCreateRequest.fixture()

        userRepository.count() shouldBe 0
        val userId = userService.createUser(request)

        userRepository.count() shouldBe 1
    }

    "유저를 저장하고 정보를 확인한다." {
        val name = "슈퍼맨"
        val mail = "superm@nawes.me"
        val phoneNumber = "01012344321"

        val request = UserCreateRequest(
            name = name,
            mail = mail,
            phoneNumber = phoneNumber,
        )

        val userId = userService.createUser(request)
        val user = userService.getUser(userId!!)

        user.id shouldBe userId
        user.name shouldBe name
        user.mail shouldBe mail
        user.phoneNumber shouldBe phoneNumber
    }

    "존재하지 않는 user 는 IllegalArgumentException 을 반환한다." {
        userRepository.count() shouldBe 0
        val exception = shouldThrow<IllegalArgumentException> {
            userService.getUser(-1)
        }

        exception.message should startWith("존재하지 않는 User 입니다.")
    }

    "유저를 수정한다." {
        val createRequest = UserCreateRequest.fixture()

        val userId = userService.createUser(createRequest)
        val user = userService.getUser(userId!!)

        user.name shouldBe createRequest.name
        user.mail shouldBe createRequest.mail
        user.phoneNumber shouldBe createRequest.phoneNumber

        // 수정 후
        val modifyRequest = UserModifyRequest(
            id = userId,
            name = "홍길동",
            mail = "abc@abc.abc",
            phoneNumber = null,
        )

        userService.modifyUser(modifyRequest)

        val modifiedUser = userService.getUser(userId)

        modifiedUser.name shouldBe "홍길동"
        modifiedUser.mail shouldBe "abc@abc.abc"
        modifiedUser.phoneNumber shouldBe createRequest.phoneNumber
    }

    "유저를 삭제한다" {
        val request = UserCreateRequest.fixture()
        userRepository.count() shouldBe 0

        val userId = userService.createUser(request)!!
        userRepository.count() shouldBe 1

        userService.deleteUser(userId)
        userRepository.count() shouldBe 0
    }

    "LikeStores 가 없을 때 빈 리스트를 반환한다." {
        val user = User.fixture()
        userRepository.save(user)

        val stores = userService.getLikeStores(user.id!!)

        stores.size shouldBe 0
    }

    "LikeStores 가 존재한다." {
        val user = User.fixture()
        userRepository.save(user)

        repeat((0..2).count()) {
            val store = Store()
//            TODO("생성자없이 val 이면 어떻게 값을 설정?")
//            store.branch_name = "branch${it}"
            storeRepository.save(store)
            likeStoreRepository.save(
                LikeStore(
                    user = user,
                    store = store,
                )
            )
        }

        val likeStores = userService.getLikeStores(user.id!!)

        likeStores.size shouldBe 3
    }

    "LikeItems 가 없을 땐 빈 리스트를 반환한다." {
        val user = User.fixture()
        userRepository.save(user)

        val stores = userService.getLikeItems(user.id!!)

        stores.size shouldBe 0
    }

    "LikeItems 가 존재한다." {
        val user = User.fixture()
        userRepository.save(user)

        repeat((0..3).count()) {
            val item = discountItemRepository.save(DiscountItem())
//            TODO("생성자없이 val 이면 어떻게 값을 설정?")
//            item.brand_name = "brand${it}"
            discountItemRepository.save(item)
            likeItemRepository.save(
                LikeItem(
                    user = user,
                    item = item,
                )
            )
        }

        val likeItems = userService.getLikeItems(user.id!!)

        likeItems.size shouldBe 4

    }

})
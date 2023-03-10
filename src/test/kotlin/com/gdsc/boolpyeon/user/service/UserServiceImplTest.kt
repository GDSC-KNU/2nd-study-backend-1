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
        likeItemRepository.deleteAll()
        likeStoreRepository.deleteAll()
        userRepository.deleteAll()
        storeRepository.deleteAll()
        discountItemRepository.deleteAll()
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

    "이미 존재하는 유저를 저장하면 예외가 발생한다." {
        val request = UserCreateRequest.fixture()
        userService.createUser(request)

        shouldThrow<IllegalArgumentException> {
            userService.createUser(request)
        }.message shouldBe "이미 존재하는 유저입니다."
    }

    "같은 이메일을 등록하려고 하면 예외가 발생한다." {
        val request1 = UserCreateRequest(
            name = "홍길동",
            mail = "aaa@aaa.aaa", // same
            phoneNumber = "01012345678",
        )

        userService.createUser(request1)

        shouldThrow<IllegalArgumentException> {
            val request2 = UserCreateRequest(
                name = "김철수",
                mail = "aaa@aaa.aaa", // same
                phoneNumber = "01087654321",
            )
            userService.createUser(request2)
        }.message shouldBe "이미 존재하는 유저입니다."
    }

    "같은 전화번호를 등록하려고 하면 예외가 발생한다." {
        val request1 = UserCreateRequest(
            name = "홍길동",
            mail = "aaa@aaa.aaa",
            phoneNumber = "01012345678", // same
        )

        userService.createUser(request1)

        shouldThrow<IllegalArgumentException> {
            val request2 = UserCreateRequest(
                name = "김철수",
                mail = "aaa@aaa.aaa",
                phoneNumber = "01087654321", // same
            )
            userService.createUser(request2)
        }.message shouldBe "이미 존재하는 유저입니다."
    }

    "서로 다른 정보를 가진 유저는 저장된다." {
        val request = UserCreateRequest(
            name = "홍길동",
            mail = "aaa@aaa.aaa",
            phoneNumber = "01012345678",
        )
        userService.createUser(request)

        val request2 = UserCreateRequest(
            name = "김철수",
            mail = "bbb@bbb.bbb",
            phoneNumber = "01087654321",
        )
        userService.createUser(request2)

        val users = userRepository.findAll()

        users.size shouldBe 2

        users[0].name shouldBe "홍길동"
        users[0].mail shouldBe "aaa@aaa.aaa"
        users[0].phoneNumber shouldBe "01012345678"

        users[1].name shouldBe "김철수"
        users[1].mail shouldBe "bbb@bbb.bbb"
        users[1].phoneNumber shouldBe "01087654321"
    }

    "이름은 같지만 다른 정보가 다르다면 성공적으로 저장된다." {
        val request = UserCreateRequest(
            name = "홍길동",
            mail = "aaa@aaa.aaa",
            phoneNumber = "01012345678",
        )
        userService.createUser(request)

        val request2 = UserCreateRequest(
            name = "홍길동",
            mail = "bbb@bbb.bbb",
            phoneNumber = "01087654321",
        )
        userService.createUser(request2)

        val users = userRepository.findAll()

        users.size shouldBe 2

        users[0].name shouldBe "홍길동"
        users[0].mail shouldBe "aaa@aaa.aaa"
        users[0].phoneNumber shouldBe "01012345678"

        users[1].name shouldBe "홍길동"
        users[1].mail shouldBe "bbb@bbb.bbb"
        users[1].phoneNumber shouldBe "01087654321"
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
//            item.brandName = "brand${it}"
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
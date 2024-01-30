import { FC } from 'react'
import styles from './ButtonProfile.module.scss'

const ButtonProfile: FC = () => {
    return (
        <button className={styles.profile}>
            <div className={styles.name}>poogiloy@yandex.ru</div>
            <div className={styles.icon}>
                <span>P</span>
                {/*<img*/}
                {/*    class="profile__img"*/}
                {/*    src="./assets/img/profile.svg"*/}
                {/*    alt=""*/}
                {/*/>*/}
            </div>
        </button>
    )
}

export default ButtonProfile

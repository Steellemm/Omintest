import { FC } from 'react'
import styles from './Logo.module.scss'
import svg from '../../assets/logo.svg'

const Logo: FC = () => {
    return (
        <div className={styles.logo}>
            <img className={styles.img} src={svg} alt="logo"/>
        </div>
    )
}

export default Logo

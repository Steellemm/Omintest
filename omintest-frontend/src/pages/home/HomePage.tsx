import { FC } from 'react'
import styles from './HomePage.module.scss'
import Top from './top/Top.tsx'
import Body from './body/Body.tsx'

const HomePage: FC = () => {
    return (
        <div className={styles.main}>
            <div className={styles.list}>
                <Top/>
                <Body/>
            </div>
        </div>
    )
}

export default HomePage

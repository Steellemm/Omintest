import { FC } from 'react'
import styles from './Top.module.scss'
import Search from '../../../components/search/Search.tsx'

const Top: FC = () => {
    return (
        <div className={styles.top}>
            <h1 className={styles.title}>Список тестов</h1>
            <Search/>
        </div>
    )
}

export default Top

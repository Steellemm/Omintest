import { FC } from 'react'
import styles from './ButtonTest.module.scss'
import { ITest } from '../../../types/pages/tests-data.ts'
import { Link } from 'react-router-dom'
import { DRAW_ROUTE } from '../../../utils/variables'


const ButtonTest: FC<ITest> = (props) => {
    return (
        <button className={styles.card}>
            <Link to={DRAW_ROUTE + '/' + props.id} className='w-full h-full p-5'>
                <span className={styles.title}>{props.title}</span>
                <span className={styles.description}>{props.description}</span>
            </Link>
        </button>
    )
}

export default ButtonTest

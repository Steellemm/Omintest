import { FC } from 'react'
import styles from './Panel.module.scss'
import logo from '../../../assets/logo.svg'
import svg from '../../../assets/svg/sprite.svg'
import { ITest } from '../../../types/pages/tests-data'
import { Link } from 'react-router-dom'
import { HOME_ROUTE } from '../../../utils/variables'

const Panel: FC<ITest> = ({ title }) => {
    return (
        <div className={styles.panel}>
            <div className={styles.logo}>
                <Link to={HOME_ROUTE} className='w-full h-full'>
                    <img className={styles.img} src={logo} alt="logo"/>
                </Link>
            </div>
            <div className={styles.test}>{title}</div>
            <button>
                <Link to={HOME_ROUTE} className='w-full h-full'>
                    <svg className={styles.icon}>
                        <use href={`${svg}#home`}></use>
                    </svg>
                </Link>
            </button>
        </div>
    )
}

export default Panel

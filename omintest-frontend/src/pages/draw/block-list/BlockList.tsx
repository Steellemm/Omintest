import { FC } from 'react'
import styles from './BlockList.module.scss'
import svg from '../../../assets/svg/sprite.svg'
import { IDrawBlockListPropsType } from '../../../types/pages/drow-block-list-props-types.ts'

const BlockList: FC<IDrawBlockListPropsType> = ({ items }) => {
    return (
        <div className={styles.blockList}>
            <div className={styles.title}>Список блоков</div>
            <div className={styles.inner}>
                {items.map(item =>
                    <button key={item.id} className={styles.btn}>
                        <svg className={styles.icon}>
                            <use href={`${svg}#plus`}/>
                        </svg>
                        <div className={styles.name}>{item.name}</div>
                    </button>
                )}
            </div>
        </div>
    )
}

export default BlockList

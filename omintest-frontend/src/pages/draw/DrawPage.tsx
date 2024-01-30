import { FC, useCallback, useContext, useEffect, useState } from 'react'
import styles from './DrawPage.module.scss'
import Panel from './panel/Panel.tsx'
import BlockList from './block-list/BlockList.tsx'
import { Stage, Container, Sprite, Text, Graphics } from '@pixi/react'
import { IListBlock } from '../../types/pages/drow-block-list-props-types.ts'
import Actions from './actions/Actions.tsx'
import Settings from './setting/Settings.tsx'
import * as PIXI from 'pixi.js'
import { Context } from '../../main'
import { useNavigate, useParams } from 'react-router-dom'
import { ITest } from '../../types/pages/tests-data'
import { HOME_ROUTE } from '../../utils/variables'
import ViewportReact from './ViewPortReact.tsx'

export interface ViewportProps {
    width: number;
    height: number;
    children?: React.ReactNode;
}

const DrawPage: FC = () => {
    const navigate = useNavigate()
    const paramsPage = useParams()
    const { tests } = useContext(Context)

    const [test, setTest] = useState<ITest>()

    useEffect(() => {
        const findTest = tests.find(t => t.id === paramsPage.id)
        setTest(findTest)
        !findTest && navigate(HOME_ROUTE)
    }, [])


    const items: IListBlock[] = [
        {
            id: '1',
            name: 'Kafka1'
        },
        {
            id: '2',
            name: 'Kafka2'
        }
    ]

    const draw = useCallback (
        (g) => {
            g.clear();
            g.beginFill(0xffffff)
            g.lineStyle(1, 'black')
            //g.drawRect(0, 0, 120, 120);
            g.drawCircle(100, 100, 50)
            g.endFill();
        }, []
    )

    if (test) {
        return (
            <div className={styles.draw} id='canvas'>
                {/* <canvas className={styles.canvas}></canvas> */}
                <Panel id={test.id} title={test.title} description={test.description}/>
                <BlockList items={items}/>
                <Actions/>
                <Settings/>
                <Stage options={{backgroundColor: 0xebebeb}} width={parent.innerWidth} height={parent.innerHeight}>
                {/* <Sprite 
                        texture={texture}
                        //width={parent.innerWidth}
                        //height={parent.innerHeight}
                    /> */}
                    <ViewportReact width={window.innerWidth} height={window.innerHeight} outer={true}>
                        <ViewportReact width={window.innerWidth} height={window.innerHeight} outer={false}>
                        <Container>
                <ViewportReact width={500} height={300} outer={false}>
                <Container x={500} y={300}>
                    <Graphics draw={draw} scale={{ x: 2, y: 2 }}/>
                    <Graphics draw={draw} x={200}/>
                    <Text text='PostgreSQL' x={160} y={185} style={new PIXI.TextStyle({fontFamily: 'TrueType', fontSize: 14})}/>
                </Container>
                </ViewportReact>
                <ViewportReact width={500} height={300} outer={false}>
                <Container x={500} y={300}>
                    <Graphics draw={draw} scale={{ x: 2, y: 2 }}/>
                    <Graphics draw={draw} x={200}/>
                    <Text text='PostgreSQL' x={160} y={185} style={new PIXI.TextStyle({fontFamily: 'TrueType', fontSize: 14})}/>
                </Container>
                </ViewportReact>
                </Container>
                </ViewportReact>
                </ViewportReact>
                </Stage>
            </div>
        )
    }
}

export default DrawPage
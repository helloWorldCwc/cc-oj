/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */

import type { JudgeCase } from './JudgeCase';
import type { JudgeConfig } from './JudgeConfig';

export type QuestionInfoAddRequest = {
    answer?: string;
    content?: string;
    judgeCaseList?: Array<JudgeCase>;
    judgeConfigDto?: JudgeConfig;
    tags?: Array<string>;
    tips?: string;
    title?: string;
};

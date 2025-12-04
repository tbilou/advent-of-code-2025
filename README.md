# advent-of-code-2025

Welcome to the Advent of Code[^aoc] Kotlin project created by [tbilou][github] using the [Advent of Code Kotlin Template][template] delivered by JetBrains.

In this repository, tbilou is about to provide solutions for the puzzles using [Kotlin][kotlin] language.

If you're stuck with Kotlin-specific questions or anything related to this template, check out the following resources:

- [Kotlin docs][docs]
- [Kotlin Slack][slack]
- Template [issue tracker][issues]


[^aoc]:
    [Advent of Code][aoc] – An annual event of Christmas-oriented programming challenges started December 2015.
    Every year since then, beginning on the first day of December, a programming puzzle is published every day for twenty-five days.
    You can solve the puzzle and provide an answer using the language of your choice.

| Day | success?   | Notes                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
|-----|------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 01  | [✓][day01] | [Secret Entrance][aoc01]: Great first puzzle. Decided to write tests this year but didn't prevent me from getting stuck on part 1 due to broken logic.                                                                                                                                                                                                                                                                                                                                               | 
| 02  | [✓][day02] | [Gift Shop][aoc02]: Trying to keep it very basic this year. No crazy collection function chaining. Decided to do the operations based on strings. For part 1 it was just about finding strings with an even number of chars and spliting it in half. For part 2 almost went down the rabbit hole of trying to calculate the groups based on prime factorization. Went the "brute force" way by calculating all the possible groups, spliting the string with chunked and collapsing it all into Set. | 
| 03  | [✓][day03]  | [Lobby][aoc03]: Solved it with brute force first, 12 nested for loops. In my defense, it was an optimized brute-force solution that ran in ~5s. I then implemented the correct algorithm and now solves it in 200ms                                                                                                                                                                                                                                                                                  | 
| 04  | [][day04]  | [][aoc04]                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | 
| 05  | [][day05]  | [][aoc05]                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | 
| 06  | [][day06]  | [][aoc06]                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | 
| 07  | [][day07]  | [][aoc07]                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | 
| 08  | [][day08]  | [][aoc08]                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | 
| 09  | [][day09]  | [][aoc09]                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | 
| 10  | [][day10]  | [][aoc10]                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | 
| 11  | [][day11]  | [][aoc11]                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | 
| 12  | [][day12]  | [][aoc12]                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | 


[aoc01]: https://adventofcode.com/2025/day/1
[aoc02]: https://adventofcode.com/2025/day/2
[aoc03]: https://adventofcode.com/2025/day/3
[aoc04]: https://adventofcode.com/2025/day/4
[aoc05]: https://adventofcode.com/2025/day/5
[aoc06]: https://adventofcode.com/2025/day/6
[aoc07]: https://adventofcode.com/2025/day/7
[aoc08]: https://adventofcode.com/2025/day/8
[aoc09]: https://adventofcode.com/2025/day/9
[aoc10]: https://adventofcode.com/2025/day/10
[aoc11]: https://adventofcode.com/2025/day/11
[aoc12]: https://adventofcode.com/2025/day/12
[day01]: src/Day01.kt
[day02]: src/Day02.kt
[day03]: src/Day03.kt
[day04]: src/Day04.kt
[day05]: src/Day05_1.kt
[day06]: src/Day06.kt
[day07]: src/Day07.kt
[day08]: src/Day08.kt
[day09]: src/Day09.kt
[day10]: src/Day10.kt
[day11]: src/Day11.kt
[day12]: src/Day12.kt

[aoc]: https://adventofcode.com
[docs]: https://kotlinlang.org/docs/home.html
[github]: https://github.com/tbilou
[issues]: https://github.com/kotlin-hands-on/advent-of-code-kotlin-template/issues
[kotlin]: https://kotlinlang.org
[slack]: https://surveys.jetbrains.com/s3/kotlin-slack-sign-up
[template]: https://github.com/kotlin-hands-on/advent-of-code-kotlin-template

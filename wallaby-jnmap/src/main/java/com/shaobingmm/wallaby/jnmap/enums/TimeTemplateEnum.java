package com.shaobingmm.wallaby.jnmap.enums;

/**
 * Nmap开发的最高优先级是性能。在本地网络对一个主机的默认扫描(nmap <hostname>)需要1/5秒。而仅仅眨眼的 时间，就需要扫描上万甚至几十万的主机。此外，一些特定的扫描选项会明显增 加扫描时间，如UDP扫描和版本检测。同样，防火墙配置以及特殊的响应速度限制也会 增加时间。Nmap使用了并行算法和许多先进的算法来加速扫描，用户对Nmap如何 工作有最终的控制权。高级用户可以仔细地调整Nmap命令，在满足时间要求的同时获得他们所关心的信息。
 *
 * 改善扫描时间的技术有：忽略非关键的检测、升级最新版本的Nmap(性能增强不断改善)。 优化时间参数也会带来实质性的变化，这些参数如下。
 *
 * --min-hostgroup <milliseconds>; --max-hostgroup <milliseconds> (调整并行扫描组的大小)
 * Nmap具有并行扫描多主机端口或版本的能力，Nmap将多个目标IP地址 空间分成组，然后在同一时间对一个组进行扫描。通常，大的组更有效。缺 点是只有当整个组扫描结束后才会提供主机的扫描结果。如果组的大小定义 为50，则只有当前50个主机扫描结束后才能得到报告(详细模式中的补充信息 除外)。
 *
 * 默认方式下，Nmap采取折衷的方法。开始扫描时的组较小， 最小为5，这样便于尽快产生结果；随后增长组的大小，最大为1024。确切的 大小依赖于所给定的选项。为保证效率，针对UDP或少量端口的TCP扫描，Nmap 使用大的组。
 * --max-hostgroup选项用于说明使用最大的组，Nmap不 会超出这个大小。--min-hostgroup选项说明最小的组，Nmap 会保持组大于这个值。如果在指定的接口上没有足够的目标主机来满足所 指定的最小值，Nmap可能会采用比所指定的值小的组。这两个参数虽然很少使用， 但都用于保持组的大小在一个指定的范围之内。
 * 这些选项的主要用途是说明一个最小组的大小，使得整个扫描更加快速。通常 选择256来扫描C类网段。对于端口数较多的扫描，超出该值没有意义。对于 端口数较少的扫描，2048或更大的组大小是有帮助的。
 * --min-parallelism <milliseconds>; --max-parallelism <milliseconds> (调整探测报文的并行度)
 * 这些选项控制用于主机组的探测报文数量，可用于端口扫描和主机发现。默认状态下， Nmap基于网络性能计算一个理想的并行度，这个值经常改变。如果报文被丢弃， Nmap降低速度，探测报文数量减少。随着网络性能的改善，理想的探测报文数量会缓慢增加。 这些选项确定这个变量的大小范围。默认状态下，当网络不可靠时，理想的并行度值 可能为1，在好的条件下，可能会增长至几百。
 * 最常见的应用是--min-parallelism值大于1，以加快 性能不佳的主机或网络的扫描。这个选项具有风险，如果过高则影响准确度，同时 也会降低Nmap基于网络条件动态控制并行度的能力。这个值设为10较为合适， 这个值的调整往往作为最后的手段。
 *
 * --max-parallelism选项通常设为1，以防止Nmap在同一时间 向主机发送多个探测报文，和选择--scan-delay同时使用非常有用，虽然 这个选项本身的用途已经很好。

 --min-rtt-timeout <milliseconds>， --max-rtt-timeout <milliseconds>， --initial-rtt-timeout <milliseconds> (调整探测报文超时)
 Nmap使用一个运行超时值来确定等待探测报文响应的时间，随后会放弃或重新 发送探测报文。Nmap基于上一个探测报文的响应时间来计算超时值，如果网络延迟比较显著 和不定，这个超时值会增加几秒。初始值的比较保守(高)，而当Nmap扫描无响应 的主机时，这个保守值会保持一段时间。

 这些选项以毫秒为单位，采用小的--max-rtt-timeout值，使 --initial-rtt-timeout值大于默认值可以明显减少扫描时间，特别 是对不能ping通的扫描(-P0)以及具有严格过滤的网络。如果使用太 小的值，使得很多探测报文超时从而重新发送，而此时可能响应消息正在发送，这使得整个扫描的时 间会增加。

 如果所有的主机都在本地网络，对于--max-rtt-timeout值来 说，100毫秒比较合适。如果存在路由，首先使用ICMP ping工具ping主机，或使用其 它报文工具如hpings，可以更好地穿透防火墙。查看大约10个包的最大往返时间，然后将 --initial-rtt-timeout设成这个时间的2倍，--max-rtt-timeout 可设成这个时间值的3倍或4倍。通常，不管ping的时间是多少，最大的rtt值不得小于100ms， 不能超过1000ms。

 --min-rtt-timeout这个选项很少使用，当网络不可靠时， Nmap的默认值也显得过于强烈，这时这个选项可起作用。当网络看起来不可靠时，Nmap仅将 超时时间降至最小值，这个情况是不正常的，需要向nmap-dev邮件列表报告bug。

 --host-timeout <milliseconds> (放弃低速目标主机)
 由于性能较差或不可靠的网络硬件或软件、带宽限制、严格的防火墙等原因， 一些主机需要很长的时间扫描。这些极少数的主机扫描往往占 据了大部分的扫描时间。因此，最好的办法是减少时间消耗并且忽略这些主机，使用 --host-timeout选项来说明等待的时间(毫秒)。通常使用1800000 来保证Nmap不会在单个主机上使用超过半小时的时间。需要注意的是，Nmap在这半小时中可以 同时扫描其它主机，因此并不是完全放弃扫描。超时的主机被忽略，因此也没有针对该主机的 端口表、操作系统检测或版本检测结果的输出。

 --scan-delay <milliseconds>; --max-scan-delay <milliseconds> (调整探测报文的时间间隔)
 这个选项用于Nmap控制针对一个主机发送探测报文的等待时间(毫秒)，在带宽 控制的情况下这个选项非常有效。Solaris主机在响应UDP扫描探测报文报文时，每秒 只发送一个ICMP消息，因此Nmap发送的很多数探测报文是浪费的。--scan-delay 设为1000，使Nmap低速运行。Nmap尝试检测带宽控制并相应地调整扫描的延迟，但 并不影响明确说明何种速度工作最佳。

 --scan-delay的另一个用途是躲闭基于阈值的入侵检测和预防 系统(IDS/IPS)。

 -T <Paranoid|Sneaky|Polite|Normal|Aggressive|Insane> (设置时间模板)

 * 上述优化时间控制选项的功能很强大也很有效，但有些用户会被迷惑。此外， 往往选择合适参数的时间超过了所需优化的扫描时间。
 * 因此，Nmap提供了一些简单的 方法，使用6个时间模板，使用时采用-T选项及数字(0 - 5) 或名称。
 * 模板名称有paranoid (0)、sneaky (1)、polite (2)、normal(3)、 aggressive (4)和insane (5)。
 * 前两种模式用于IDS躲避，Polite模式降低了扫描 速度以使用更少的带宽和目标主机资源。默认模式为Normal，因此-T3 实际上是未做任何优化。
 * Aggressive模式假设用户具有合适及可靠的网络从而加速 扫描。Insane模式假设用户具有特别快的网络或者愿意为获得速度而牺牲准确性。
 * 用户可以根据自己的需要选择不同的模板，由Nmap负责选择实际的时间值。 模板也会针对其它的优化控制选项进行速度微调。
 * 例如，-T4 针对TCP端口禁止动态扫描延迟超过10ms，-T5对应的值为5ms。 模板可以和优化调整控制选项组合使用，但模板必须首先指定，否则模板的标准值 会覆盖用户指定的值。建议在扫描可靠的网络时使用 -T4，即使 在自己要增加优化控制选项时也使用(在命令行的开始)，从而从这些额外的较小的优化 中获益。
 * 如果用于有足够的带宽或以太网连接，仍然建议使用-T4选项。 有些用户喜欢-T5选项，但这个过于强烈。有时用户考虑到避免使主机 崩溃或者希望更礼貌一些会采用-T2选项。他们并没意识到-T Polite选项是如何的慢，这种模式的扫描比默认方式实际上要多花10倍的时间。默认时间 选项(-T3)很少有主机崩溃和带宽问题，比较适合于谨慎的用户。不进行 版本检测比进行时间调整能更有效地解决这些问题。
 * 虽然-T0和-T1选项可能有助于避免IDS告警，但 在进行上千个主机或端口扫描时，会显著增加时间。对于这种长时间的扫描，宁可设定确切的时间 值，而不要去依赖封装的-T0和-T1选项。
 * T0选项的主要影响是对于连续扫描，在一个时间只能扫描一个端口， 每个探测报文的发送间隔为5分钟。T1和T2选项比较类似， 探测报文间隔分别为15秒和0.4秒。T3是Nmap的默认选项，包含了并行扫描。 T4选项与 --max-rtt-timeout 1250 --initial-rtt-timeout 500 等价，最大TCP扫描延迟为10ms。T5等价于 --max-rtt-timeout 300 --min-rtt-timeout 50 --initial-rtt-timeout 250 --host-timeout 900000，最大TCP扫描延迟为5ms。
 *
 * @author luyb@servyou.com.cn
 * @version $Id: TimeTemplate.java v 0.1 2017/1/23 1:44 luyb Exp $$
 */
public enum TimeTemplateEnum {
    PARANOID(0, "paranoid"),
    SNEAKY(1, "sneaky"),
    POLITE(2, "polite"),
    NORMAL(3, "normal"),
    AGGRESSIVE(4, "aggressive"),
    INSANE(5, "insane");

    private final int code;

    private final String name;

    TimeTemplateEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * Getter for property 'code'.
     *
     * @return code Value for property 'code'.
     */
    public int getCode() {
        return code;
    }

    /**
     * Getter for property 'name'.
     *
     * @return name Value for property 'name'.
     */
    public String getName() {
        return name;
    }
}
